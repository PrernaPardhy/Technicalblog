package blog.common;

import java.io.*;
import java.util.*;

public class FileOperations <T> {
    private static final FileOperations fileOperations = new FileOperations();

    private FileOperations() {
    }

    public static FileOperations getInstance() {
        return fileOperations;
    }

    List<T> readAllFiles(String dirPath) {

        synchronized (fileOperations) {
            List<T> arrayList = new ArrayList<T>();
            File file = new File(dirPath);
            File[] files = file.listFiles();
            if (files != null) {

                for (File f : files) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(f);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        T readObject = (T) objectInputStream.readObject();
                        if (readObject != null) {
                            arrayList.add(readObject);
                        }

                    } catch (FileNotFoundException e) {

                    } catch (IOException e) {
                        e.printStackTrace();

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            return arrayList;
        }
    }

    List<T> readRecentFiles(final int numberOfFiles, final String dirLocation) {
        synchronized (fileOperations) {
            Map<Long, File> sortByModificationDate = new TreeMap<Long, File>(Collections.reverseOrder());
            //This map will be containing all the files in the descending order of the modification time of a file
            //We will add all files in this arrayList to be returned
            List<T> arrayList = new ArrayList<T>();
            try {
                //Declare a File type object & the dirPath input is given to a file.
                File file = new File(dirLocation);
                //files is a File type array to contain all files
                File[] files = file.listFiles();
                if (files != null) {
                    //Run a for loop for all the files & add all files into a map.
                    //The value of last modification time of a file behaves as a key & hence all files will be stored in
                    // descending order of the last modification time(Why descending?? because reverseOrder() method is used)
                    for (File f : files) {
                        sortByModificationDate.put(f.lastModified(), f);
                    }

                    //Now we will store the number of files to be read in count variable & run a for loop equal to count
                    // of times.
                    int count = numberOfFiles;
                    for (Long modifiedOn : sortByModificationDate.keySet()) {

                        //Reads the file input stream from file represented by the File type object
                        //This class is used to read streams of raw bytes from a file
                        FileInputStream fileInputStream = new FileInputStream(sortByModificationDate.get(modifiedOn));

                        //Declare an ObjectInputStream type object by passing the object of FileInputStream type in its constructor.
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                        T readObject = (T) objectInputStream.readObject();
                        if (readObject != null) {
                            arrayList.add(readObject);
                        }
                        count--;
                        if (count <= 0) break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
            return arrayList;
        }
    }




    T readFile(final String filePrefix, final String uniqueId) {
        synchronized (fileOperations) {
            T readObject = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(filePrefix + uniqueId));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                readObject = (T) objectInputStream.readObject();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return readObject;
        }
    }

    boolean deleteFile(final String filePrefix, final String uniqueId){
        synchronized (fileOperations){
            File file=new File(filePrefix+uniqueId);
            return file.delete();
        }
    }

    public T writeToFile(final String filePrefix,  final T object,final String suffix){
        synchronized (fileOperations){
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(new File(filePrefix+suffix),true);
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(object);
                objectOutputStream.close();
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return object;
        }
    }

}





