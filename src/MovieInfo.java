import java.nio.file.Path;

public class MovieInfo extends MediaInfo{

    private final int year;

    public MovieInfo(String title, String extension, int year) {
        super(title, extension);
        this.year = year;
    }

    @Override
    //movie path - The Batman (2001)/The Batman (2001).mkv (or mp4)
    public Path getJellyfinPath() {
        String fileName = String.format("%s (%d)", title, year);


        return Path.of(fileName, fileName + "." + extension);
    }

    @Override
    public String getLibraryFolder() {
        return "Movies";
    }

    public int getYear(){
        return year;
    }



}