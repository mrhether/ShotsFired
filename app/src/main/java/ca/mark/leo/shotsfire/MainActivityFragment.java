package ca.mark.leo.shotsfire;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.mark.leo.shotsfire.models.Shot;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Bind(R.id.recyler_view)
    protected RecyclerView mReyclerView;

    private final ShotsRecyclerAdapter mAdapter = new ShotsRecyclerAdapter();
    private final Firebase ref = ShotsFiredApplication.getFirebaseRef();
    private LinearLayoutManager mLayoutManager;

    private MediaPlayer mp;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        mp = MediaPlayer.create(MainActivityFragment.this.getContext(), R.raw.shots);
        mLayoutManager = new LinearLayoutManager(getContext());

        mReyclerView.setHasFixedSize(true);
        mReyclerView.setLayoutManager(mLayoutManager);
        mReyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ref.child("shots").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Shot> shots = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Shot shot = postSnapshot.getValue(Shot.class);
                    shots.add(0, shot);
                }
                mAdapter.setShots(shots);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ref.child("shots").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mp.start();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
}
