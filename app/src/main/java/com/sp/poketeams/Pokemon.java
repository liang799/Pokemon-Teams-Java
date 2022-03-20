package com.sp.poketeams;

public class Pokemon {
    private String pokename;
    private String poketype;

    public Pokemon(String pokename, String poketype) {
        this.pokename = pokename;
        this.poketype = poketype;
    }

    public String getPokename() {
        return pokename;
    }

    public String getPoketype() {
        return poketype;
    }

}
