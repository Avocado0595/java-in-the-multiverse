package org.example;

import java.util.Date;
import java.util.List;

abstract class Album {
    protected String title;
    protected Date releaseDate;
    protected String releaseBrand;
    protected List<String> songList;
    public Album(){};
    public Album(String title, Date releaseDate, String releaseBrand, List<String> songList){
        this.title = title;
        this.releaseBrand = releaseBrand;
        this.releaseDate =releaseDate;
        this.songList = songList;
    }
    public String toString(){
        return String.format("title: %s, brand: %s, date: %s",this.title, this.releaseBrand, this.releaseDate.toString());
    }
}
