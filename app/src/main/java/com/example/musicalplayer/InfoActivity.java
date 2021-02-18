package com.example.musicalplayer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    public ImageView playButton;
    private TextView titleNowPlayingTextView;
    private TextView artistNowPlayingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        /**################################# Now Playing Section ##################################*/

        /**##### Title and Artist ##########*/
        titleNowPlayingTextView = findViewById(R.id.song_title_info);
        artistNowPlayingTextView = findViewById(R.id.song_artist_info);
        displayTextOfNowPlayingSong();

        /**##### Play Button ##########*/

        playButton = findViewById(R.id.play_button_info);
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

        /**################################# About this song ##################################*/

        Song clickedOnSong = (Song) getIntent().getSerializableExtra("Song");

        TextView titleTextView = findViewById(R.id.title_placeholder);
        titleTextView.setText(clickedOnSong.getSongTitle());

        TextView artistTextView = findViewById(R.id.artist_placeholder);
        artistTextView.setText(clickedOnSong.getSongArtist());

        ImageView albumCoverImageView = findViewById(R.id.album_cover_image_view);
        albumCoverImageView.setImageResource(clickedOnSong.getSongCover());

        TextView infoTextView = (TextView) findViewById(R.id.info_text_view);
        infoTextView.setText(clickedOnSong.getSongInfo());

    }


    /**
     * On resume of this activity, this method sets the correct symbol for the play button and
     * the correct song for the "Now Playing" section, since both might have been udpated in MainActivity.
     */
    @Override
    protected void onResume() {
        super.onResume();
        displayPlayButton();
        displayTextOfNowPlayingSong();
    }

    /**
     * This method sets the symbol of the play button to either play or pause. This depends
     * on the value saved as playBUttonStatus in the NowPlayingInformation class. 0 stands for
     * play and 1 for pause.
     */
    public void displayPlayButton() {
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
    public void displayTextOfNowPlayingSong() {
        titleNowPlayingTextView.setText(NowPlayingInformation.getTitle());
        artistNowPlayingTextView.setText(NowPlayingInformation.getArtist());
    }
}
