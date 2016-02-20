package ca.mark.leo.shotsfire;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.mark.leo.shotsfire.models.Shot;

public class MainActivity extends AppCompatActivity {

    Firebase ref = ShotsFiredApplication.getFirebaseRef();

    @Bind(R.id.sender)
    protected TextView mSenderTextView;

    @Bind(R.id.target)
    protected TextView mTargetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String victim = mTargetTextView.getText().toString();
                String sender = mSenderTextView.getText().toString();

                Snackbar.make(view, "Firing shots at the homie " + victim, Snackbar.LENGTH_LONG).show();

                Shot shot = new Shot(sender, victim);
                ref.child("shots").push().setValue(shot);

            }
        });



    }

}
