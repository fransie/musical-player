package com.example.musicalplayer;

import java.io.Serializable;

/**
 * The Song class saves title, artist, information and album cover of a song. It implements
 * Serializable interface so that it can be put as an Extra with an Intent.
 */
class Song implements Serializable {
    private String songTitle;
    private String songArtist;
    private String songInfo;
    private int songCoverResource;

    Song(String title, String artist, String info, int songCoverResource) {
        this.songTitle = title;
        this.songArtist = artist;
        this.songInfo = info;
        this.songCoverResource = songCoverResource;
    }

    String getSongArtist() {
        return songArtist;
    }

    String getSongTitle() {
        return songTitle;
    }

    String getSongInfo() {
        return songInfo;
    }

    int getSongCover() {
        return songCoverResource;
    }

}

