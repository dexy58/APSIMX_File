package hr.fer.tel;

import hr.fer.tel.apsimx.APSIMX_Files;

import java.io.*;

public class Main {
    public static void main(String[] args) { //preko komandne linije uzmi input parametar za koji tip file-a (npr. maize, potato...)
        CreateApsimxFile();
        WriteToApsimxFile();
        RunSimulation();
    }

    private static void WriteToApsimxFile(){ //input paramet za koji file (maize, potato,...)
        try {
            FileWriter myWriter = new FileWriter("C:\\Maize.apsimx");
            myWriter.write(APSIMX_Files.MAIZE_FILE);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void CreateApsimxFile(){ //input paramet za koji file (maize, potato,...)
        try {
            File myObj = new File("C:\\Maize.apsimx");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void RunSimulation(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        processBuilder.command("cmd.exe", "/c", "dotnet apsim.dll run f Maize.apsimx");

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
