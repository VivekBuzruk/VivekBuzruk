package coin.sarvatech.dAddressBookIndia;

import java.io.Serializable;
import java.util.Comparator;

/** An object of this class maintains information about a single individual
 *  in the address book
 */
public class Person implements Serializable
{
	private static String [] fieldNames = { "First Name", "Last Name", "Address", "City", "State", "Pincode", "Phone", "Email" };
    private static String [] modifiableFieldNames = { "Address", "City", "State", "Pincode", "Phone", "Email" };
    
    // Stored information about the person
    
    private String firstName, lastName;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String phone;
    private String email;
    
    /** Constructor - attributes specified individually
     *
     *  @param firstName the person's first name
     *  @param lastName the person's last name
     *  @param address the person's address
     *  @param city the person's city
     *  @param state the person's state
     *  @param pincode the person's pincode
     *  @param phone the person's phone
     *  @param email the person's primary email
     */
    public Person(String firstName,
                  String lastName, 
                  String address,
                  String city,
                  String state,
                  String pincode,
                  String phone,
                  String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.phone = phone;
        this.email = email;
    }
    
    public static String[] getAddressEntryFields() {
    	return fieldNames;
    }
    
    public static String[] getModifiableAddressEntryFields() {
    	return modifiableFieldNames;
    }
    
   public String getAddressBookEntryValue(String fieldName) {
		switch(fieldName) {
		case "First Name":
			return this.getFirstName();
		case "Last Name":
			return this.getLastName();
		case "Address":
			return this.getAddress();
		case "City":
			return this.getCity();
		case "State":
			return this.getState();
		case "Pincode":
			return this.getPincode();
		case "Phone":
			return this.getPhone();
		case "Email":
			return this.getEmail();    			
		}
    	return null;
    }
    
    public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	/** Get the full name of a person in the form last, first
     * 
     *  @return the person's name
     */
    public String getFullName()
    {
          return fullName(firstName, lastName);
    }
    
    /** Get the full name corresponding to a given first and last name
     *	
     *	@param firstName the first name
     *	@param lastName the last name
     *	@return the corresponding full name, formatted appropriately
     */
    public static String fullName(String firstName, String lastName)
    {
		return lastName + ", " + firstName;    
    }
     
    /** Accessor for the person's address
     *
     *  @return the person's address
     */
    public String getAddress()
    {
        return address;
    }
    
    /** Accessor for the person's city
     *
     *  @return the person's city
     */
    public String getCity()
    {
        return city;
    }
    
    /** Accessor for the person's state
     *
     *  @return the person's state
     */
    public String getState()
    {
        return state;
    }
    
    /** Accessor for the person's pincode
     *
     *  @return the person's pincode
     */
    public String getPincode()
    {
        return pincode;
    }
    
    /** Accessor for the person's phone
     *
     *  @return the person's phone
     */
    public String getPhone()
    {
        return phone;
    }
    
    /** Accessor for the person's email
    *
    *  @return the person's email
    */
   public String getEmail()
   {
       return email;
   }
   
    /** Update the person with new information.  Note that the name
     *  cannot be changed, but the other information can be
     *
     *  @param address the person's new address
     *  @param city the person's new city
     *  @param state the person's new state
     *  @param pincode the person's new pincode
     *  @param phone the person's new phone
     *  @param email the person's new email
     */
    public void update(String address,
                       String city,
                       String state,
                       String pincode,
                       String phone,
                       String email)
    {
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.phone = phone;
        this.email = email;
    }
    
    /** Test to see whether this person contains a search criterion
     * 
     *  @param criterion the search criterion
     *  @return true if the criterion is contained in any field
     */
    public boolean contains(String criterion)
    {
        return firstName.contains(criterion) ||
               lastName.contains(criterion) ||
               address.contains(criterion) ||
               city.contains(criterion) ||
               state.contains(criterion) ||
               pincode.contains(criterion) ||
               phone.contains(criterion) ||
               email.contains(criterion);
    }
    

    /** Comparator for comparing two persons by alphabetical order of name
     */
    public static class CompareByName implements Comparator<Person>
    {
        /** Compare two Persons by name
         *
         *  @param person1 the first person
         *  @param person2 the second person
         *  @return a negative number if person1 belongs before person2 in
         *          alphabetical order of name; 0 if they are equal; a
         *          positive number if person1 belongs after person2
         */
        public int compare(Person person1, Person person2)
        {
            int result = person1.lastName.compareTo(person2.lastName);
            if (result == 0)
                return person1.firstName.compareTo(person2.firstName);
            else
               return result;
        }       
    }
    
    /** Comparator for comparing two persons by order of pincode code, with ties
     *  broken by name
     */
    public static class CompareByPincode implements Comparator<Person>
    {
        /** Compare two Persons by pincode, with ties broken by name
         *
         *  @param person1 the first person
         *  @param person2 the second person
         *  @return a negative number if person1 belongs before person2 in
         *          order of pincode with ties broken by name; 0 if they are equal
         *          on both name and pincode; a positive number if person1 belongs 
         *          after person2 in order of pincode with ties broken by name.  
         */
        public int compare(Person person1, Person person2)
        {
            int result = person1.pincode.compareTo(person2.pincode);
            if (result == 0)
                return new CompareByName().compare(person1, person2);
            else
                return result;
        }       
    }
    
    /** Comparator for comparing two persons by order of email, with ties
     *  broken by name
     */
    public static class CompareByEmail implements Comparator<Person>
    {
        /** Compare two Persons by email, with ties broken by name
         *
         *  @param person1 the first person
         *  @param person2 the second person
         *  @return a negative number if person1 belongs before person2 in
         *          order of email with ties broken by name; 0 if they are equal
         *          on both name and email; a positive number if person1 belongs 
         *          after person2 in order of email with ties broken by name.  
         */
        public int compare(Person person1, Person person2)
        {
            int result = person1.email.compareTo(person2.pincode);
            if (result == 0)
                return new CompareByName().compare(person1, person2);
            else
                return result;
        }       
    }
}
