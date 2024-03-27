package ma.emsi.contact_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddEditContact extends AppCompatActivity
{
    private ImageView imageEt;
    EditText nameEt,phoneEt,emailEt,noteEt;
    String image,name,phone,email,note;

    private DbHelper dbHelper;

    //permission Constants

    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        imageEt =findViewById(R.id.profileIv);
        nameEt=findViewById(R.id.nameEt);
        phoneEt=findViewById(R.id.phoneEt);
        emailEt=findViewById(R.id.emailEt);
        noteEt=findViewById(R.id.noteEt);
        dbHelper=new DbHelper(this);

    }

    public void chooseImage(View view)
    {
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
        Toast.makeText(this, "Choose image", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImagePicker.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            imageEt.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public  void save_data(View view)
    {
        image=uri.toString();
        name=nameEt.getText().toString();
        phone=phoneEt.getText().toString();
        email=emailEt.getText().toString();
        note=noteEt.getText().toString();
        String timeStamp=System.currentTimeMillis()+"";

        if(name.isEmpty()||phone.isEmpty()||email.isEmpty()||note.isEmpty())
        {
            Toast.makeText(this, "please , Fill in all the fields.", Toast.LENGTH_SHORT).show();
        }else
        {
            long id=dbHelper.insertContact(image,name,phone,email,note,timeStamp,timeStamp);
            Toast.makeText(this, "data saved with id = "+id, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}