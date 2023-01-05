import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.Input;
import input.InputMovie;
import input.InputUser;
import platform.RegisteredUser;
import platform.Database;
import platform.Executable;
import platform.Movie;
import java.io.File;
import java.io.IOException;

public final class Main {
    private static final int MAGIC_NUMBER = 6;
    private Main() {
        throw new UnsupportedOperationException("This is the Main class & can't be instantiated");
    }

    /** Main */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        readInput(objectMapper, args, Database.getContent());
        //Reads the input and fills the database accordingly

        ArrayNode outputArray = objectMapper.createArrayNode();
        //The node array that represents the final output

        Executable.getExe().run(outputArray);
        //The execution od the program itself that fills the output array

        writeOutput(objectMapper, args, outputArray);
        //Writes the output in the result files
    }

    /** This method was created to read the input and populate
     *  the database for users, movies and actions */
    private static void readInput(final ObjectMapper objectMapper, final String[] args,
                                  final Database database)
                                  throws IOException {
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        for (int i = 0; i < inputData.getUsers().size(); i++) {
            InputUser auxInputUser = inputData.getUsers().get(i);
            RegisteredUser auxRegisteredUser = new RegisteredUser(auxInputUser);
            database.getUsersDB().add(auxRegisteredUser);
        }
        for (int i = 0; i < inputData.getMovies().size(); i++) {
            InputMovie auxInputMovie = inputData.getMovies().get(i);
            Movie auxMovie = new Movie(auxInputMovie);
            database.getMoviesDB().add(auxMovie);
        }
        database.setActionsDB(inputData.getActions());
    }

    /** This method was created to write the output of each
     *  test in the output file associated with it */
    private static void writeOutput(final ObjectMapper objectMapper, final String[] args,
                                    final ArrayNode outputArray) throws IOException {
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), outputArray);
        char[] inPath = args[0].toCharArray();
        String outPath;
        if (inPath[inPath.length - MAGIC_NUMBER] == '0') {
            outPath = "checker\\resources\\out\\" + args[1] + "10.json";
        } else {
            outPath = "checker\\resources\\out\\" + args[1]
                    + inPath[inPath.length - MAGIC_NUMBER] + ".json";
        }
        objectWriter.writeValue(new File(outPath), outputArray);
    }
}
