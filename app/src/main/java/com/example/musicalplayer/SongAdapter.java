package com.example.musicalplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class is a custom Adapter for the Song class and is used to populate the ListView in the
 * MainActivity.
 */
public class SongAdapter extends ArrayAdapter<Song> {
    private Context c;

    SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
        this.c = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Song currentSong = getItem(position);

        //For each row in the ListView, a title String and an artist String are set
        TextView titleTextView = listItemView.findViewById(R.id.title_text_view);
        titleTextView.setText(currentSong.getSongTitle());
        TextView artistTextView = listItemView.findViewById(R.id.artist_text_view);
        artistTextView.setText(currentSong.getSongArtist());

        //Info button with onClickListener that opens the InfoActivity and sends the current
        //as an Extra with the Intent
        TextView infoTextView = listItemView.findViewById(R.id.info_button);
        infoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openInfoActivity = new Intent(c, InfoActivity.class);
                openInfoActivity.putExtra("Song", currentSong);
                c.startActivity(openInfoActivity);
            }
        });

        //Playbutton per song that sets title and artist of the clicked song in NowPlayingInformation
        //class, displays them in the "Now Playing" section and sets the play button symbol in the
        //"Now Playing" section to pause so that the user can stop the song
        TextView playButtonPerSongTextView = (TextView) listItemView.findViewById(R.id.play_button_per_song);
        playButtonPerSongTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NowPlayingInformation.setTitle(currentSong.getSongTitle());
                NowPlayingInformation.setArtist(currentSong.getSongArtist());
                MainActivity.displayTextOfNowPlayingSong();
                setPlayButtonToPause();
            }
        });

        return listItemView;
    }

    /**
     * Updates the status of the play button in NowPlayingInformation class to pause and displays
     * the pause symbol in the "Now Playing" section of the MainActivity.
     */
    private void setPlayButtonToPause() {
        NowPlayingInformation.setPlayButtonStatus(1);
        MainActivity.displayPlayButton();
    }

}
