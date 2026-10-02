import java.nio.file.Path;

public abstract class MediaInfo {

    protected final String title;
    protected final String extension;

    public MediaInfo(String title, String extension){
        this.title = title;
        this.extension = extension;
    }

    public abstract Path getJellyfinPath();

    public String getTitle(){
        return title;
    }

    public String getExtension(){
        return extension;
    }

    public abstract String getLibraryFolder();
}
