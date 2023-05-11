package org.example;

import java.util.Date;
import java.util.List;

public class ComplexAlbum extends  Album{
    public ComplexAlbum() {
    }

    public ComplexAlbum(String title, Date releaseDate, String releaseBrand, List<String> songList) {
        super(title, releaseDate, releaseBrand, songList);
    }
}
