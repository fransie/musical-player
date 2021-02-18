package com.example.musicalplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static ImageView playButton;
    public static TextView titleNowPlayingTextView;
    public static TextView artistNowPlayingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**################################# Now Playing Section ##################################*/

        /**##### Title and Artist ##########*/
        //default message "choose a song" is displayed if no song has been chosen so far
        titleNowPlayingTextView = findViewById(R.id.song_title_main);
        artistNowPlayingTextView = findViewById(R.id.song_artist_main);
        NowPlayingInformation.setTitle("Choose a song");
        NowPlayingInformation.setArtist("");
        displayTextOfNowPlayingSong();

        /**##### Play Button ##########*/
        //sets an onClickListener to the play button, so that it toggles between the play
        //and pause symbol when pressed
        playButton = findViewById(R.id.play_button_main);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (NowPlayingInformation.getPlayButtonStatus() == 0) {
                    NowPlayingInformation.setPlayButtonStatus(1);
                    displayPlayButton();
                } else if (NowPlayingInformation.getPlayButtonStatus() == 1) {
                    NowPlayingInformation.setPlayButtonStatus(0);
                    displayPlayButton();
                }

            }
        });


        /**################################# Songs ListView ##################################*/

        //saves Song objects in an ArrayList and displays them via a ListView and a custom Adapter
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("The Fox", "Ylvis", getString(R.string.fox_info), R.drawable.fox));
        songs.add(new Song("Africa", "Toto", getString(R.string.africa_info), R.drawable.africa));
        songs.add(new Song("I like to move it", "Reel 2 Real", getString(R.string.move_it_info), R.drawable.move_it));
        SongAdapter songsAdapter = new SongAdapter(this, songs);
        final ListView listView = findViewById(R.id.songs_list_view);
        listView.setAdapter(songsAdapter);
    }

    /**
     * On resume of this activity, this method sets the correct symbol for the play button, since
     * its status might have been udpated in InfoActivity.
     */
    @Override
    protected void onResume() {
        super.onResume();
        displayPlayButton();
    }

    /**
     * This method sets the symbol of the play button to either play or pause. This depends
     * on the value saved as playBUttonStatus in the NowPlayingInformation class. 0 stands for
     * play and 1 for pause.
     */
    public static void displayPlayButton() {
        if (NowPlayingInformation.getPlayButtonStatus() == 0) {
            playButton.setImageResource(R.drawable.play);
        } else if (NowPlayingInformation.getPlayButtonStatus() == 1) {
            playButton.setImageResource(R.drawable.pause);
        }
    }

    /**
     * This method sets the Title and Artist TextViews in the "Now Playing§ section
     * to the currently played song, which is saved in the NowPlayingInformation class.
     */
    public static void displayTextOfNowPlayingSong() {
        titleNowPlayingTextView.setText(NowPlayingInformation.getTitle());
        artistNowPlayingTextView.setText(NowPlayingInformation.getArtist());
    }

}
