package coin.sarvatech.dAddressBookIndia;

/** Main class for the Address Book
 */
public class AddressBookApplication
{
    private FileSystem fileSystem;
    private AddressBookController controller;
    
    /** Constructor - create the objects which do all the work.
     */    
    private AddressBookApplication()
    {
        fileSystem = new FileSystem();
        controller = new AddressBookController(fileSystem);                
    }

    /** Main method for program
     */    
    public static void main(String [] args)
    {
        new AddressBookApplication();
    }

}
