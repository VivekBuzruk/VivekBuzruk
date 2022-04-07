package coin.sarvatech.dAddressBookIndia;

import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import com.opencsv.CSVWriter;

/** An object of this class manages interaction between the address book
 *  program and the file system of the computer it is running on.
 */

public class FileSystem
{
    /** Read a stored file
     *
     *  @param file the file specification for the file to read
     *  @return the AddressBook object stored in the file
     *
     *  @exception IOException if there is a problem reading the file
     *  @exception ClassCastException if the file does not contain an
     *             AddressBook
     *  @exception ClassNotFoundException if the file does not contain
     *             an AddressBook, and the class it does contain is not
     *             found - this should never happen
     */
    public AddressBook readFile(File file) throws IOException, 
                                                  ClassNotFoundException
    {
        ObjectInputStream stream = 
            new ObjectInputStream(new FileInputStream(file));
        AddressBook result = (AddressBook) stream.readObject();
        result.setFile(file);
        result.setUnchangedSinceLastSave();
        defaultDirectory = file.getParent();
        return result;
    }
    
    /** Save an address book to a file
     *
     *  @param addressBook the AddressBook to save
     *  @param file the file specification for the file to create
     *
     *  @exception IOException if there is a problem writing the file
     */
    public void  saveFile(AddressBook addressBook, File file) throws IOException
    {
        ObjectOutputStream stream = 
            new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(addressBook);
        addressBook.setFile(file);
        addressBook.setUnchangedSinceLastSave();
        defaultDirectory = file.getParent();
    }

    
    /** Save an address book to CSV file
     *
     *  @param addressBook the AddressBook to save
     *  @param file the file specification for the file to create
     *
     *  @exception IOException if there is a problem writing the file
     */
    public void  saveCSVFile(String[] headerNames, ListIterator <Person> myAddressBookEntries, File file) throws IOException
    {
    	//     private static String [] fieldNames = { "First Name", "Last Name", "Address", 
        //                                             "City", "State", "Pincode", "Phone", "Email" };
        // create FileWriter object with file as parameter
        FileWriter outputfile = new FileWriter(file);
  
        // create CSVWriter object filewriter object as parameter
        CSVWriter writer = new CSVWriter(outputfile);
        
        writer.writeNext(headerNames);
        
        while (myAddressBookEntries.hasNext()) {
           Person myAddressBookEntry = myAddressBookEntries.next();
           String thisLine[] = new String[headerNames.length];
        	
           
        	int columnIx = 0;
        	for (String myHeader : headerNames) {
        		String fieldValue = myAddressBookEntry.getAddressBookEntryValue(myHeader);
        		assert (fieldValue != null);
        		thisLine[columnIx++] = fieldValue;
        	}
        	writer.writeNext(thisLine);
        }
        writer.close();
    }
    
   /** Get the default directory for open/save/print
     * 
     *  @return the default directory, or the user's home directory if there
     *          is none
     */
    public String getDefaultDirectory()
    {
        return defaultDirectory;
    }
        
    // The default directory to use for open/save/print
    
    private String defaultDirectory;
}
