package coin.sarvatech.dAddressBookIndia;

import javax.swing.JApplet;

/** Applet version of address book application
 */
public class AddressBookApplet extends JApplet
{
    /** Initialization for the applet
     */    
    public void init()
    {
        FileSystem fileSystem = new FileSystem();
        AddressBookController controller = new AddressBookController(fileSystem);
    }
}
