import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class FileRenamer{

    private final Path libraryRoot;
    private final boolean dryRun;
    private final boolean copyMode;


    public FileRenamer(Path libraryRoot, boolean dryRun, boolean copyMode){
        this.libraryRoot = libraryRoot;
        this.dryRun = dryRun;
        this.copyMode = copyMode;
    }

    public Path rename(Path source, MediaInfo info) throws IOException{
        Path target = libraryRoot.resolve(info.getLibraryFolder()).resolve(info.getJellyfinPath());
        Path parent = target.getParent();
        Files.createDirectories(parent);


        if(dryRun){
            String op = copyMode ? "COPY" : "MOVE";
            System.out.println("[DRY RUN]" + source + " -> " + target);
        }else{
            if (copyMode) {
                Files.copy(source, target);
            } else {
                Files.move(source, target);
            }
        }

        return target;
    }



}