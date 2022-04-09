package coin.sarvatech.dAddressBookIndia ;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Vector;

/** An object of this class maintains the collection of Person objects that
 *  constitute an address book
 */
public class AddressBook 
{
    // The collection of persons is stored in a vector
    private Vector<Person> collection;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    // Other information that must be maintained
    private File file;
    private boolean changedSinceLastSave;
    
    
    /** Constructor - create a new, empty address book
     */
    public AddressBook()
    {
        collection = new Vector<Person>();
        file = null;
        changedSinceLastSave = false;
        // setupTests();
    }
    
    public String[] getAddressEntryFields() {
    	return Person.getAddressEntryFields();
    	
    }

    public String[] getModifiableAddressEntryFields() {
    	return Person.getModifiableAddressEntryFields();
    }

    public ListIterator <Person> getAddressEntriesIterator() {
    	return collection.listIterator();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
    
    /** Provide a list of the the names of all the persons in the collection
     *
     *  @return an array of Strings, each representing the name of one person
     */
    public String [] getNames()
    {
        String [] result = new String [collection.size()];
        for (int i = 0; i < collection.size(); i ++)
            result[i] = collection.elementAt(i).getFullName();
        return result;
    }
    
    
    /** Add a new Person to the collection
     *
     *  @param firstName the person's first name
     *  @param lastName the person's last name
     *  @param address the person's address
     *  @param city the person's city
     *  @param state the person's state
     *  @param pincode the person's pincode
     *  @param phone the person's phone
     *  @param phone the person's email
     */
    public void addPerson(String firstName,
                          String lastName,
                          String address,
                          String city,
                          String state,
                          String pincode,
                          String phone,
                          String email)
    {
        collection.addElement(new Person(firstName, 
                                         lastName, 
                                         address, 
                                         city, 
                                         state, 
                                         pincode, 
                                         phone,
                                         email));
        changedSinceLastSave = true;

        this.pcs.firePropertyChange("collection", null, collection);
    }
    
	public void addPerson(Person newPerson) {
		collection.addElement(newPerson);
		changedSinceLastSave = true;
		
		this.pcs.firePropertyChange("collection", null, collection);
	}

    /** Provide current information about a person
     *
     *  @param name the desired name
     *  @return an array of Strings, each containing one piece of stored
     *          information about this person, or null if no such person exists
     */
    public String [] getPersonInformation(String name)
    {
        int index = findIndex(name);
        if (index >= 0)
        {
            Person person = collection.elementAt(index);
            String [] result = 
                { person.getAddress(),
                  person.getCity(),
                  person.getState(),
                  person.getPincode(),
                  person.getPhone(),
                  person.getEmail()
                };
            return result;
        }
        else
            return null;
    }
    
    /** Update stored information about a person
     *
     *  @param name the name of the person (which cannot be updated)
     *  @param address the person's new address
     *  @param city the person's new city
     *  @param state the person's new state
     *  @param pincode the person's new pincode
     *  @param phone the person's new phone
     *
     *  @exception IllegalArgumentException if the specified person does not exist
     */
    public void updatePerson(String name,
                             String address,
                             String city,
                             String state,
                             String pincode,
                             String phone,
                             String email) throws IllegalArgumentException
    {
        int index = findIndex(name);
        if (index >= 0)
        {
            collection.elementAt(index).update(address, city, state, pincode, phone, email);
            changedSinceLastSave = true;

            this.pcs.firePropertyChange("collection", null, collection);
        }
        else
            throw new IllegalArgumentException("No such person");
    }
    
    /** Remove a specific person from the collection
     *
     *  @param name the desired name
     *
     *  @exception IllegalArgumentException if the specified person does not exist
     */
    public void removePerson(String name) throws IllegalArgumentException
    {
        int index = findIndex(name);
        if (index >= 0)
        {
            collection.removeElementAt(index);
            changedSinceLastSave = true;

            this.pcs.firePropertyChange("collection", null, collection);

        }
        else
            throw new IllegalArgumentException("No such person");
    }
    
    /** Sort the collection by name
     */
    public void sortByName()
    {
        Collections.sort(collection, new Person.CompareByName());
        changedSinceLastSave = true;

        this.pcs.firePropertyChange("collection", null, collection);

    }

    /** Sort the collection by Pincode
     */
    public void sortByPincode()
    {
        Collections.sort(collection, new Person.CompareByPincode());
        changedSinceLastSave = true;

        this.pcs.firePropertyChange("collection", null, collection);

    }
    
    /** Search the collection for a person matching given criteria
     * 
     *  @param criterion the criterion for the search
     *  @param startingIndex the position to start the search from
     *  @return the index of the first person at or after startingIndex which
     *          has a field that contains the criterion, or -1 if there is no such person
     */
    public int search(String criterion, int startingIndex)
    {
        for (int i = startingIndex; i < collection.size(); i ++)
            if (collection.elementAt(i).contains(criterion))
                return i;
        return -1;
    }
    
    /** Get the File this address book was most recently read from or saved to
     *
     *  @return the most recent File - if any - null if none
     */
    public File getFile()
    {
        return file;
    }
    
    /** Set the File this address book was most recently read from or saved to
     *
     *  @param file the file just used to read or save this object
     */
    public void setFile(File file)
    {
        this.file = file;
    }
    
    /** Get the title of this address book - based on the most recently used file
     *
     *  @return the title of this address book - "Untitled" if none
     */
    public String getTitle()
    {
        if (file == null)
            return "Untitled";
        else
            return file.getName();
    }
    
    /** Print the collection of persons in order.
     *
     *  @param writer the writer to print to
     */
    public void printMailingLabels(PrintWriter writer)
    {
        for (int i = 0; i < collection.size(); i ++)
        {
            Person person = collection.elementAt(i);
            writer.println(person.getFullName());
            writer.println(person.getAddress());
            writer.println(person.getCity() + " " + person.getState() + " " +
                               person.getPincode());
            writer.println();
        }
    }
    
    /** Find out whether this address book has been changed since last open / save
     *
     *  @return true if this address book has been changed since the last open / save;
     *          false if not 
     */
    public boolean getChangedSinceSaved()
    {
        return changedSinceLastSave;
    }
    
    /** Record that an open/save operation has taken place, rendering this
     *  address book unchanged since the last such operation
     */
    public void setUnchangedSinceLastSave()
    {
        changedSinceLastSave = false;
    }
    
    /** Auxiliary to various methods - get the Person object from the collection
     *  that corresponds to a given name
     *
     *  @param name the desired name
     *  @return the index where the corresponding person occurs, or -1 if the
     *          person does not occur
     */
    private int findIndex(String name)
    {
        for (int i = 0; i < collection.size(); i ++)
        {
            Person person = collection.elementAt(i);
            if (person.getFullName().equals(name))
                return i;
        }
        
        return -1;
    }
    
    // Method to facilitate testing
    
    void setupTests()
    {
        addPerson("Dev", "Kapil", "1, Captain, Righ hand, Right arm fast medium", "Gurugram",
                             "Haryana", "122001", "+911111983001", "kapil_dev@example.com");
        addPerson("Amarnath", "Mohinder", "2, VC, Right hand, Right arm medium", "New Delhi",
                             "Delhi", "110001", "+911111983002", "mohinder_amarnath@example.com");
        addPerson("Azad", "Kirti", "Right hand, Right arm off spinner", "New Delhi",
                             "Delhi", "110026", "+911111983003", "kirti_azad@example.com");
        addPerson("Binny", "Roger", "Right hand, Right arm fast medium", "Bengaluru",
                             "Karnataka", "560001", "+911111983004", "roger_binny@example.com");
        addPerson("Gavaskar", "Sunil", "Right hand, Right arm medium, Right arm offbreak", "Mumbai",
                             "MH", "400001", "+911111983005", "sunil_gavaskar@example.com");
        addPerson("Patil", "Sandeep", "Right hand, Right arm medium", "Mumbai",
                             "MH", "400002", "+911111983006", "sandeep_patil@example.com");
        addPerson("Sandhu", "Balwinder", "Right handed, Right arm medium fast", "Mumbai",
                             "MH", "400003", "+911111983007", "balwinder_sandhu@example.com");
        addPerson("Shastri", "Ravi", "Right hand, Slow left arm orthodox", "Mumbai",
        		             "MH", "400004", "+911111983008", "ravi_shastri@example.com");
        addPerson("Vengsarkar", "Dilip", "Right hand, Right arm medium", "Mumbai",
        		             "MH", "400005", "+911111983009", "dilip_vengsarkar@example.com"); 
		/***
		Player	Date of Birth	Batting Style	Bowling Style	First Class team
		Kapil Dev (c)	06 January 1959	Right hand	Right arm fast-medium	India Haryana cricket team
		Mohinder Amarnath (vc)	24 September 1950	Right hand	Right arm medium	India Delhi
		Kirti Azad	2 January 1959	Right-hand	Right-arm off-spinner	India Delhi
		Roger Binny	19 July 1955	Right-hand	Right-arm fast-medium	India Karnataka
		Sunil Gavaskar	10 July 1949	Right hand	Right arm medium
		Right arm offbreak	India Mumbai
		Syed Kirmani (wk)	29 December 1949	Right-handed	Wicket-keeper	India Karnataka
		Madan Lal	20 March 1951	Right-hand	Right-arm medium	India Delhi
		Sandeep Patil	18 August 1956	Right-hand	Right-arm medium	India Mumbai cricket team
		Balwinder Sandhu	3 August 1956	Right-handed	Right arm medium-fast	India Mumbai cricket team
		Yashpal Sharma	11 August 1954	Right-handed	Right-arm medium	India Punjab
		Ravi Shastri	27 May 1962	Right-hand	Slow left-arm orthodox	India Mumbai cricket team
		Krishnamachari Srikkanth	21 December 1959	Right hand	Right-arm medium
		Right-arm offbreak	India Tamil Nadu
		Sunil Valson	2 October 1958	Right-hand	Left-arm medium	India Delhi
		Dilip Vengsarkar	6 April 1956	Right hand	Right arm medium	India Mumbai cricket team
		***/
    }
}
