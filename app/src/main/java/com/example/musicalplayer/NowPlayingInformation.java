package com.example.musicalplayer;

/**
 * This class keeps track of the data of the "Now Playing" section in the MainActivity as
 * well as in the InfoActivity. It saves the currently playing song and the status of the
 * play button (play or pause).
 */
class NowPlayingInformation {
    private static int playButtonStatus = -1;
    private static String title;
    private static String artist;

    static int getPlayButtonStatus() {
        return playButtonStatus;
    }

    static String getArtist() {
        return artist;
    }

    static String getTitle() {
        return title;
    }

    static void setArtist(String artistOfSongNowPlayingString) {
        NowPlayingInformation.artist = artistOfSongNowPlayingString;
    }

    static void setPlayButtonStatus(int playButtonDrawable) {
        NowPlayingInformation.playButtonStatus = playButtonDrawable;
    }

    static void setTitle(String titleOfSongNowPlayingString) {
        NowPlayingInformation.title = titleOfSongNowPlayingString;
    }

}
