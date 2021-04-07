package com.matanhassin.matkonli.model;
import android.content.ContentResolver;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.matanhassin.matkonli.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class ModelFirebase {

    public interface Listener<T>{
        void onComplete();
        void onFail();
    }

    public static void loginUser(final String email,final String password, final Listener<Boolean> listener){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (email != null && !email.equals("") && password != null && !password.equals(""))
        {
            if (firebaseAuth.getCurrentUser() != null)
            {
                firebaseAuth.signOut();
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>()
            {
                @Override
                public void onSuccess(AuthResult authResult)
                {
                    Toast.makeText(MyApplication.context, "Hello :)", Toast.LENGTH_SHORT).show();
                    setUserData(email);
                    listener.onComplete();
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MyApplication.context, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    listener.onFail();
                }
            });
        }
        else {
            Toast.makeText(MyApplication.context, "You must fill in all of the fields", Toast.LENGTH_SHORT).show();
        }
    }


    public static void registerUser(final String username, final String password, final String email, final Uri imageUri, final Listener<Boolean> listener){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            firebaseAuth.signOut();
        }

        if(username!=null
        && !username.equals("") && password !=null && !password.equals("")
        && email != null && !email.equals("") && imageUri !=null){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(MyApplication.context,"User registered", Toast.LENGTH_SHORT).show();
                    CreateUser(username,email,imageUri);
                    listener.onComplete();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MyApplication.context,"Failed registering user",Toast.LENGTH_SHORT).show();
                    listener.onFail();
                }
            });
        }

        else{
            Toast.makeText(MyApplication.context,"You must fill in all of the fields and profile image",Toast.LENGTH_SHORT).show();
            listener.onFail();
        }
    }

    private static void CreateUser(final String username, final String email, Uri imageUri){
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images");
        if (imageUri != null){
            String imageName = username + "." + getExtension(imageUri);
            final StorageReference imageRef = storageReference.child(imageName);

            UploadTask uploadTask = imageRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return imageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){

                        Map<String,Object> data = new HashMap<>();
                        data.put("profileImageUrl", task.getResult().toString());
                        data.put("username", username);
                        data.put("email", email);
                        data.put("info", "NA");
                        firebaseFirestore.collection("userData").document(email).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void avoid) {
                                if (firebaseAuth.getCurrentUser() != null){
                                    firebaseAuth.signOut();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MyApplication.context, "Fails to create user and upload data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if (!task.isSuccessful()){
                        Toast.makeText(MyApplication.context, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(MyApplication.context, "Please choose a profile image", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getExtension(Uri uri)
    {
        try{
            ContentResolver contentResolver = MyApplication.context.getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

        } catch (Exception e) {
            Toast.makeText(MyApplication.context, "Register page: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static void setUserData(final String email)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();;
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        db.collection("userProfileData").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                String username = User.getInstance().getUsername();
                String userprofileImageUrl = User.getInstance().getUserprofileImageUrl();
                String userPassword = User.getInstance().getUserPassword();
                String userAddress = User.getInstance().getUserAddress();
                String userEmail = User.getInstance().getUserEmail();
                String userId = User.getInstance().getUserId();

                if (task.isSuccessful()){
                    username = (String) task.getResult().get("username");
                    userprofileImageUrl = (String) task.getResult().get("profileImageUrl");
                    userPassword = (String) task.getResult().get("password");
                    userAddress = (String) task.getResult().get("address");
                    userEmail = email;
                    userId = firebaseAuth.getUid();
                }
            }
        });
    }

    public void addRecipe(Recipe recipe, Model.Listener<Boolean> listener) { }
}
