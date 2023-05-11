package org.example;

import java.util.Date;
import java.util.List;

public class ArtistAlbum extends Album{
    private String artistName;
    public ArtistAlbum(){
        super();
    }
    public void setArtistName(String artistName){
        this.artistName=artistName;
    }

    public ArtistAlbum(String title, Date releaseDate, String releaseBrand, List<String> songList, String artistName) {
        super(title, releaseDate, releaseBrand, songList);
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return super.toString()+", artist: "+this.artistName;
    }
}
