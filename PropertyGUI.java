
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class PropertyGUI extends JFrame
{
	private JDesktopPane theDesktop;
	private JComboBox<String> selectOwner =  new JComboBox<String>();
	private String [] selectOwnerArray = {"Select An Owner"};
	private File oName;
	private File pName;
	
	//declare ownersList and PropertyList
	private ArrayList<Owner> ownersList = new ArrayList<Owner>();
	private ArrayList<Property> propertyList = new ArrayList<Property>();

	

	public PropertyGUI()
	{
		super("Property Input Screen");

	
		selectOwner = new JComboBox<String>(selectOwnerArray);
		

		theDesktop = new JDesktopPane();

		JMenuBar bar = new JMenuBar();

		JMenu readFilesMenu = new JMenu("Read Files");
		JMenuItem readOwnersItem = new JMenuItem("Read Owners");
		JMenuItem readPropertiesItem = new JMenuItem("Read Properties");

		readFilesMenu.add(readOwnersItem);
		readFilesMenu.add(readPropertiesItem);

		readOwnersItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{

				jFileOChooser();
				
				Scanner input; 
				String name; 
				String streetAddress;
				String city;
				String state;
				int zip;
				String line;
				String [] values;
			
				
						
					
				try
				{
					input = new Scanner(oName);
					while(input.hasNext())
					{
						line = input.nextLine();	
						values = line.split(";");
					
						name = values[0];
						streetAddress = values[1];
						city = values [2];
						state = values [3];
						zip = Integer.parseInt(values[4]);

						ownersList.add(new Owner(values[0],new Address(values[1],values[2],values[3],Integer.parseInt(values[4]))));
						selectOwner.addItem(values[0]);
					}		
				}
		
				catch(IOException ioe)
				{
					ioe.printStackTrace();	
		
				}

				
			}
		
		});	//end of listener if not commented out
	

		readPropertiesItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			
				jFilePChooser();
				
		
				Scanner input; 	
				int accountNumber;	
				Address address; //need to add these classes to the folder		
				double marketValue;	
				Date datePurchased;	
				int squareFeet;		
				Property.PropertyTier propertyTier;	
				boolean floodZone;	
				ResidentialProperty.Subdivision subdivision;	
				String businessName;		
				String stateCode;	
		
				String line;
				String values[];
					     
			
				try
				{
				    	input = new Scanner(pName);
					while(input.hasNext())
					{
						line = input.nextLine();
						values = line.split(";");

						if(values[0].equals("Residential"))
						{
							accountNumber = Integer.parseInt(values[2]);
                                        		address = new Address(values[3],values[4],values[5],Integer.parseInt(values[6]));
                                       	 		marketValue = Double.parseDouble(values[7]);
                                        		datePurchased = new Date(Integer.parseInt(values[8]),Integer.parseInt(values[9]),Integer.parseInt(values[10]));
                                        		squareFeet = Integer.parseInt(values[11]);
                                        
								
                                        			if(values[12].equals("TIER1"))
                                        			{
                                           	 			propertyTier = Property.PropertyTier.TIER1;                           
                                        			}
                                        			else if(values[12].equals("TIER2"))
                                        			{
                                            				propertyTier = Property.PropertyTier.TIER2;
                                        			}
                                        			else if(values[12].equals("TIER3"))
                                        			{
                                            				propertyTier = Property.PropertyTier.TIER3;
                                        			}
                                        			else
                                        			{
                                            				propertyTier = Property.PropertyTier.TIER4;
                                        			}  
                                        
                                        			floodZone = Boolean.parseBoolean(values[13]);
								
                                        			
                                       				if(values[14].equals("Kingly Estate"))
                                        			{
                                            				subdivision = ResidentialProperty.Subdivision.KINGLY_ESTATES;
                                        			}
                                        			else if(values[14].equals("Stately Estates"))
                                        			{
                                            				subdivision = ResidentialProperty.Subdivision.STATELY_ESTATES;
                                        			}
                                        			else if(values[14].equals("King Manor"))
                                        			{
                                            				subdivision = ResidentialProperty.Subdivision.KING_MANOR;
                                        			}
                                        			else if(values[14].equals("Green Gables"))
                                        			{
                                            				subdivision = ResidentialProperty.Subdivision.GREEN_GABLES;
                                        			}
                                        			else
                                        			{
                                            				subdivision = ResidentialProperty.Subdivision.NONE;
                                        			}  
                                        
					
                                        			for(int i = 0; i < ownersList.size();i++)
                                        			{
                                            				if(values[1].equals(ownersList.get(i).getName()))
                                            				{
                                               					ownersList.get(i).addProperty(new ResidentialProperty(accountNumber,address,marketValue, datePurchased,squareFeet,propertyTier,floodZone,subdivision));
                                            				}
                                       				}
                                        
                                        			propertyList.add(new ResidentialProperty(accountNumber,address,marketValue, datePurchased,squareFeet,propertyTier,floodZone,subdivision));
				            
						}
                               	 		else
                                		{
                                    			accountNumber = Integer.parseInt(values[2]);
                                        		address = new Address(values[3],values[4],values[5],Integer.parseInt(values[6]));
                                        		marketValue = Double.parseDouble(values[7]);
                                        		datePurchased = new Date(Integer.parseInt(values[8]),Integer.parseInt(values[9]),Integer.parseInt(values[10]));
                                        		squareFeet = Integer.parseInt(values[11]);
                                        	
						
                                        		if(values[12].equals("TIER1"))
                                        		{
                                            			propertyTier = Property.PropertyTier.TIER1;                           
                                        		}
                                        		else if(values[12].equals("TIER2"))
                                        		{
                                            			propertyTier = Property.PropertyTier.TIER2;
                                        		}
                                        		else if(values[12].equals("TIER3"))
                                        		{
                                            			propertyTier = Property.PropertyTier.TIER3;
                                        		}
                                        		else
                                        		{
                                            			propertyTier = Property.PropertyTier.TIER4;
                                        		}   
                                        
                                        		businessName = values[13];
                                        		stateCode = values[14];                                                                             
                                        
                                        		for(int i = 0; i < ownersList.size(); i++)
                                        		{
                                            			if(values[1].equals(ownersList.get(i).getName()))
								{
                                                			ownersList.get(i).addProperty(new CommercialProperty(accountNumber,address,marketValue,datePurchased,squareFeet,propertyTier,businessName,stateCode));
								}
                                        		}
                                        
                                        		propertyList.add(new CommercialProperty(accountNumber,address,marketValue,datePurchased,squareFeet,propertyTier,businessName,stateCode));    
                                            
                                		} // end else
						

						
					} // end While	

				}	
				catch(IOException ioe)
				{
					ioe.printStackTrace();

				}

			}
		});  
	

		JMenu addMenu = new JMenu("Add");
		JMenuItem addOwnerItem = new JMenuItem("Add Owner");
		JMenuItem addResPropertyItem = new JMenuItem("Add Residential Property");
		JMenuItem addCommPropertyItem = new JMenuItem("Add Commercial Property");

		addMenu.add(addOwnerItem);
		addMenu.add(addResPropertyItem);
		addMenu.add(addCommPropertyItem);

		addOwnerItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JInternalFrame frame = new JInternalFrame("Add Owner", true,true,true,true);
				AddOwnerPanel aop = new AddOwnerPanel();
				frame.add(aop);
				frame.pack();
				theDesktop.add(frame);
				frame.setVisible(true);
			}
		});

		addResPropertyItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JInternalFrame frame = new JInternalFrame("Add Residential Property", true,true,true,true);
				AddResidentialPanel arp = new AddResidentialPanel();
				frame.add(arp);
				frame.pack();
				theDesktop.add(frame);
				frame.setVisible(true);
			}
		});

		addCommPropertyItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JInternalFrame frame = new JInternalFrame("Add Commercial Property", true,true,true,true);
				AddCommercialPanel acp = new AddCommercialPanel();
				frame.add(acp);
				frame.pack();
				theDesktop.add(frame);
				frame.setVisible(true);
			}
		});

		JMenu finishMenu = new JMenu("Finish");
		JMenuItem writeOwnersItem = new JMenuItem("Write Owners");
		JMenuItem exitItem = new JMenuItem("Exit");

		finishMenu.add(writeOwnersItem);
		finishMenu.add(exitItem);

		writeOwnersItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				writeOwners();	
			}
		});

		exitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				calculateTaxes();
				System.exit(0);
			}
		});

		bar.add(readFilesMenu);
		bar.add(addMenu);
		bar.add(finishMenu);

		add(theDesktop);
		setJMenuBar(bar);

	}
	
	
	public File jFileOChooser()
	{	

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int result = fileChooser.showOpenDialog(null);

		
		if (result == JFileChooser.CANCEL_OPTION)
				
			System.exit(1);
				
		
		oName = fileChooser.getSelectedFile();
		
		return oName;

	}

	public File jFilePChooser()
	{	

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int result = fileChooser.showOpenDialog(null);

		
		if (result == JFileChooser.CANCEL_OPTION)
				
			System.exit(1);
				
		
		pName = fileChooser.getSelectedFile();
		
		return pName;

	}

	public void writeOwners()
	{
		ObjectOutputStream output;
		
		try
		{
			output = new ObjectOutputStream(new FileOutputStream("owners.ser"));
		
			for(Owner o:ownersList)
				output.writeObject(o);
		
			output.close();
		}

		catch(IOException ioe)
		{
			ioe.printStackTrace();
		
		}

	}
	
	
	public void calculateTaxes()
	{
		String msg = "";     
                ArrayList<Property> pList = new ArrayList<Property>();
                                
                	for(Owner o: ownersList)
                        {
                        	String exemption = "";
                                double tax = 0;
                                double exemptAmt = 0;
                                double marketValue;
                                pList = o.getPropertyList();
                                    
                                	for(Property p: pList)
                                    	{
                                        	tax += p.calculateTaxes();
                                        	marketValue = p.getMarketValue();
                                        
                                        	if(p instanceof ResidentialProperty)
                                        	{
                                            		exemption += String.format("      %s\n",((ResidentialProperty)p).calculateExemption());
                                            		exemptAmt = (marketValue * Exemption.COUNTY/100) + (marketValue * Exemption.CITY/100) + (marketValue * Exemption.SCHOOL/100) + (marketValue * Exemption.MEDICAL/100);
                                            		((ResidentialProperty)p).setExemption(exemptAmt);
                                        	}
                                        
                                    	}
                                    
                                o.setTotalTaxes(tax - exemptAmt);
                                    
                                msg += String.format("%s   $%,.2f\n", o.getName(), tax);
                                msg += exemption;
                                msg += String.format("      %s   $%,.2f\n\n", "Total Tax",o.getTotalTaxes());		                                                                              
                       }                               
                JOptionPane.showMessageDialog(null,msg);
	}                                


	class AddOwnerPanel extends JPanel
	{
		 JLabel nameLabel;
		 JTextField nameField;
		 JLabel streetLabel; 
		 JTextField streetField;
		 JLabel cityLabel;
		 JTextField cityField;
		 JLabel stateLabel;
		 JTextField stateField;
		 JLabel zipLabel;
		 JTextField zipField;
		 JLabel submitLabel;
		 JButton submitButton;

		 ButtonHandler handler;

		public AddOwnerPanel()
		{
			setLayout(new GridLayout(6,2));

			nameLabel = new JLabel("Name");
			nameField = new JTextField(20);
			streetLabel = new JLabel("Street");
			streetField = new JTextField(20);
			cityLabel = new JLabel("City");
			cityField = new JTextField(20);
			stateLabel = new JLabel("State");
			stateField = new JTextField(20);
			zipLabel = new JLabel("Zip");
			zipField = new JTextField(20);
			submitLabel = new JLabel("Submit When Done");
			submitButton = new JButton("Submit");

			ButtonHandler handler = new ButtonHandler();
			submitButton.addActionListener(handler);

			add(nameLabel);
			add(nameField);
			add(streetLabel);
			add(streetField);
			add(cityLabel);
			add(cityField);
			add(stateLabel);
			add(stateField);
			add(zipLabel);
			add(zipField);
			add(submitLabel);	
			add(submitButton);

		}
		class ButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
			{
				String name;
				String street;
				String city;
				String state;	
				int zip;

				if(ae.getSource()==submitButton)
				{
					if(checkAddOwnerInput())
					{
						name = nameField.getText();
						street = streetField.getText();
						city = cityField.getText();
						state = stateField.getText();
						zip = Integer.parseInt(zipField.getText());
						ownersList.add(new Owner(name, new Address(street, city, state, zip)));
						selectOwner.addItem(name);
						clearOwner();  
					}
					
				}
			}
		} 

		
		public boolean checkAddOwnerInput()
		{
			boolean input = true;
			
			if(nameField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter name");
				nameField.requestFocus();
				input = false;
			}

			else if(streetField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Street");
				streetField.requestFocus();
				input = false;
			} 
			
			else if(cityField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter City");
				cityField.requestFocus();
				input = false;
			} 

			else if(stateField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter State");
				stateField.requestFocus();
				input = false;
			} 
			
			else if(zipField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Zip Code");
				zipField.requestFocus();
				input = false;

			}
					
			
			else if(!zipField.getText().isEmpty())
			{
				try
				{
					Integer.parseInt(zipField.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null,"Please Enter an Integer for Zip Code");
					zipField.setText("");
					zipField.requestFocus();
					input = false;
				}		
										
			} 
				
			return input;
		
		}

		public void clearOwner()
		{
			nameField.setText("");
			streetField.setText("");
			cityField.setText("");
			stateField.setText("");
			zipField.setText("");   

			nameField.requestFocus();

		}
	} 

	class AddResidentialPanel extends JPanel
	{
		
		JLabel rStreetLabel;
		JTextField rStreetField;
		JLabel rCityLabel;
		JTextField rCityField;
		JLabel rStateLabel;
		JTextField rStateField;
		JLabel rZipLabel;
		JTextField rZipField;
		JLabel rSelectOwnerLabel;
		//JComboBox<String> selectOwner =  new JComboBox<String>();
		JLabel rAccountLabel;
		JTextField rAccountField;
		JLabel rMarketLabel;
		JTextField rMarketField;
		JLabel rDateLabel;
		JTextField rDateField;
		JLabel rSquareFeetLabel;
		JTextField rSquareFeetField;
		JLabel propertyTierLabel;
		JRadioButton tier1Button;
		JRadioButton tier2Button;
		JRadioButton tier3Button;
		JRadioButton tier4Button;
		JLabel subdivisionLabel;
		JComboBox<String> selectSubdivision;
		JLabel floodZoneLabel;
		JRadioButton yesFloodButton;
		JRadioButton noFloodButton;
		JLabel submitLabel;
		JButton submitButton;

		ButtonGroup tierGroup;
		ButtonGroup yesnoGroup;

		JPanel propertyTierPanel;
		JPanel floodZonePanel;
	
		ButtonHandler buttonHandler;

		//String ownerArray [] = {"Owner 1", "Owner 2"};
		String subdivisionArray [] = {"NONE", "KINGLY_ESTATES", "STATELY_ESTATES", "KING_MANOR", "GREEN_GABLES"};

		public AddResidentialPanel()
		{
			setLayout(new GridLayout(13,2));

			rStreetLabel = new JLabel("Street");
			rStreetField = new JTextField(20);
			rCityLabel = new JLabel("City");
			rCityField = new JTextField(20);
			rStateLabel = new JLabel("State");
			rStateField = new JTextField(20);
			rZipLabel = new JLabel("Zip");
			rZipField = new JTextField(20);
			rSelectOwnerLabel = new JLabel("Select an Owner");
			//selectOwner = new JComboBox<Owner>(ownerArray);
			//selectOwner = new JComboBox<String>(ownerArray);
			rAccountLabel = new JLabel("Account Number");
			rAccountField = new JTextField(20);
			rMarketLabel = new JLabel("Market Value");
			rMarketField = new JTextField(20);
			rDateLabel = new JLabel("Purchase Date");
			rDateField = new JTextField(20);
			rSquareFeetLabel = new JLabel("Square Feet");
			rSquareFeetField = new JTextField(20);
			propertyTierLabel = new JLabel("Property Tier");
			tier1Button = new JRadioButton("TIER 1");
			tier2Button = new JRadioButton("TIER 2");
			tier3Button = new JRadioButton("TIER 3");
			tier4Button = new JRadioButton("TIER 4");
			subdivisionLabel = new JLabel("Subdivision");
			selectSubdivision = new JComboBox<String>(subdivisionArray);
			floodZoneLabel = new JLabel("FloodZone");
			yesFloodButton = new JRadioButton("YES");
			noFloodButton = new JRadioButton("NO");
			submitLabel = new JLabel("Click To Submit");
			submitButton = new JButton("Submit");
		
			propertyTierPanel = new JPanel();
			propertyTierPanel.setLayout(new GridLayout(1,4));
			propertyTierPanel.add(tier1Button);
			propertyTierPanel.add(tier2Button);
			propertyTierPanel.add(tier3Button);
			propertyTierPanel.add(tier4Button);

			floodZonePanel = new JPanel();
			floodZonePanel.setLayout(new GridLayout(1,2));
			floodZonePanel.add(yesFloodButton);
			floodZonePanel.add(noFloodButton);

			tierGroup = new ButtonGroup();
			yesnoGroup = new ButtonGroup();

			tierGroup.add(tier1Button);
			tierGroup.add(tier2Button);
			tierGroup.add(tier3Button);
			tierGroup.add(tier4Button);
			
			yesnoGroup.add(yesFloodButton);
			yesnoGroup.add(noFloodButton);

			

			add(rStreetLabel);
			add(rStreetField);
			add(rCityLabel);
			add(rCityField);
			add(rStateLabel);
			add(rStateField);
			add(rZipLabel);
			add(rZipField);
			add(rSelectOwnerLabel);
			add(selectOwner);
			add(rAccountLabel);
			add(rAccountField);
			add(rMarketLabel);
			add(rMarketField);
			add(rDateLabel);
			add(rDateField);
			add(rSquareFeetLabel);
			add(rSquareFeetField);
			add(propertyTierLabel);
			add(propertyTierPanel);
			add(subdivisionLabel);
			add(selectSubdivision);
			add(floodZoneLabel);
			add(floodZonePanel);
			add(submitLabel);
			add(submitButton);

			ButtonHandler buttonHandler = new ButtonHandler();
			submitButton.addActionListener(buttonHandler);
			
	
		}
		class ButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				String street;
				String city;
				String state;
				int zip;
				Property.PropertyTier propertyTier;
				int accountNumber;
				double marketValue;
				String Date;
				int month;
				int day;
				int year;
				int squareFeet;
				ResidentialProperty.Subdivision subdivision;
				String ownerName;
				boolean floodZone;
				String businessName;	
				int stateCode;
				Date purchaseDate;
				String [] residentialList;
				


				
				
				if(ae.getSource()==submitButton)
				{
					
					if(checkResidentialInput())
					{
						
						
						street = rStreetField.getText();
						city = rCityField.getText();
						state = rStateField.getText();
						zip = Integer.parseInt(rZipField.getText());
						accountNumber = Integer.parseInt(rAccountField.getText());
						ownerName = selectOwner.getSelectedItem().toString();
						marketValue = Double.parseDouble(rMarketField.getText());
						Date = rDateField.getText();
						purchaseDate = createDate(Date);
						squareFeet = Integer.parseInt(rSquareFeetField.getText());
						subdivision = determineDivision(selectSubdivision.getSelectedItem().toString());
						propertyTier = determineTier();
						floodZone = determineFlood();
						propertyList.add(new ResidentialProperty(accountNumber, new Address(street,city,state,zip),marketValue,purchaseDate,squareFeet,propertyTier,floodZone,subdivision));
						

						for(Owner o: ownersList)
						{
							if(ownerName.equals(o.getName()))
							{
								o.addProperty(new ResidentialProperty(accountNumber,new Address(street,city,state,zip),marketValue,purchaseDate,squareFeet,propertyTier,floodZone,subdivision));
							}
						}  			
		
						//System.out.println(ownersList);
						
						clearResidential(); 
						 
					}
				}
			}
		} 

		public boolean checkResidentialInput()
		{
			boolean input = true;

			
			if(rStreetField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Street");
				rStreetField.requestFocus();
				input = false;
			} 
			
			else if(rCityField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter City");
				rCityField.requestFocus();
				input = false;
			} 

			else if(rStateField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter State");
				rStateField.requestFocus();
				input = false;
			} 
			
			else if(rZipField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Zip Code");
				rZipField.requestFocus();
				input = false;
			} 
				
			else if(selectOwner.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(null,"Please Select an Owner");
				input = false;
			}
		

			else if (rAccountField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Account Number");
				rAccountField.requestFocus();
				input = false;
			}

			
			else if (rMarketField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Market Value");
				rMarketField.requestFocus();
				input = false;

			}

			
			else if (rDateField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Purchase Date");
				rDateField.requestFocus();
				input = false;
			}

			
			else if (rSquareFeetField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Square Feet");
				rSquareFeetField.requestFocus();
				input = false;

			}
		
			
			else if(!tier1Button.isSelected() && !tier2Button.isSelected() && !tier3Button.isSelected() && !tier4Button.isSelected())
			{
				JOptionPane.showMessageDialog(null, "Please Select TIER");
				input = false;
			}
			else if(selectSubdivision.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(null,"Please Select Subdivision");
				input = false;
			}
			else if(!yesFloodButton.isSelected() && !noFloodButton.isSelected())
			{
				JOptionPane.showMessageDialog(null,"Please Select Flood Zone");
				input = false;
			}
			else if(!checkDate())
            		{
                		JOptionPane.showMessageDialog(null, "Please enter a valid purchase date in the following format: xx/xx/xx");
                		rDateField.setText("");
                		rDateField.requestFocus();
                		input = false;
            		}

			
		        else
				input = checkNumberInput();


			
			return input;

		}

		public boolean checkNumberInput()
		{

				try
				{
					Integer.parseInt(rZipField.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null,"Please Enter an Integer for Zip Code");
					rZipField.setText("");
					rZipField.requestFocus();
					return false;
				}



				try
				{
					Integer.parseInt(rAccountField.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null,"Please Enter an Integer for Account Number");
					rAccountField.setText("");
					rAccountField.requestFocus();
					return false;
				}


				try
				{
					Double.parseDouble(rMarketField.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null,"Enter a Double for Market Value");
					rMarketField.setText("");
					rMarketField.requestFocus();
					return false;
				}
				


				try
				{
					Integer.parseInt(rSquareFeetField.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null,"Enter an Integer for Square Feet");
					rSquareFeetField.setText("");
					rSquareFeetField.requestFocus();
					return false;
								
				}


			return true;

	
		}

		public boolean checkDate()
		{
			boolean input = true;
			String line = rDateField.getText();
			String [] values = line.split("/");
		
			if(values.length >3)
			{
				input = false;
			}
			else if(values[0].length() != 2)
            		{
                		input = false;
            		}
            		else if(values[1].length() != 2)
            		{
                		input = false;
            		}
            		else if(values[2].length() != 2)
            		{
                		input = false;
            		}
            
            		return input;

		}

		public Date createDate(String d)
        	{
            		String[]values;
            
            		values = d.split("/");
            
            		return(new Date(Integer.parseInt(values[0]),Integer.parseInt(values[1]), Integer.parseInt(values[2])));           
        	}		

		public void clearResidential()
		{
			rStreetField.setText("");
			rCityField.setText("");
			rStateField.setText("");
			rZipField.setText("");
			selectOwner.setSelectedIndex(0);
			rAccountField.setText("");
			rMarketField.setText("");
			rDateField.setText("");
			rSquareFeetField.setText("");
			tierGroup.clearSelection();
			selectSubdivision.setSelectedIndex(0);
			yesnoGroup.clearSelection();
		}

		public Property.PropertyTier determineTier()
        	{
        		Property.PropertyTier propertyTier;
            
            		if(tier1Button.isSelected())
            		{
                		propertyTier = Property.PropertyTier.TIER1;
            		}
            		else if(tier2Button.isSelected())                            
            		{
                	propertyTier = Property.PropertyTier.TIER2;
            		}
            		else if(tier3Button.isSelected())
            		{
                		propertyTier = Property.PropertyTier.TIER3;
            		}
            		else
            		{
                		propertyTier = Property.PropertyTier.TIER4;
            		}
            
            		return propertyTier;
                                        
        	}

		public ResidentialProperty.Subdivision determineDivision(String s)
        	{
            		ResidentialProperty.Subdivision subdivision;
            
            		if(s.equals("KINGLY_ESTATES"))
                		subdivision = ResidentialProperty.Subdivision.KINGLY_ESTATES;
            		else if(s.equals("STATELY_ESTATES"))
                		subdivision = ResidentialProperty.Subdivision.STATELY_ESTATES;
            		else if(s.equals("KING_MANOR"))
                		subdivision = ResidentialProperty.Subdivision.KING_MANOR;
            		else
                		subdivision = ResidentialProperty.Subdivision.GREEN_GABLES;
            
            		return subdivision;                                   
        	}

		public boolean determineFlood()
        	{
            		boolean floodZone;

            		if(yesFloodButton.isSelected())
            		{
                		floodZone = true;
            		}
            		else
            		{
                		floodZone = false;
            		}
            		return floodZone;
        	}		

				
		

	} // end ResidentialPanel

	class AddCommercialPanel extends JPanel
	{
		
		JLabel cStreetLabel;
		JTextField cStreetField;
		JLabel cCityLabel;
		JTextField cCityField;
		JLabel cStateLabel;
		JTextField cStateField;
		JLabel cZipLabel;
		JTextField cZipField;
		JLabel cSelectOwnerLabel;
		//JComboBox<String> selectOwner =  new JComboBox<String>();
		JLabel cAccountLabel;
		JTextField cAccountField;
		JLabel cMarketLabel;
		JTextField cMarketField;
		JLabel cDateLabel;
		JTextField cDateField;
		JLabel cSquareFeetLabel;
		JTextField cSquareFeetField;
		JLabel propertyTierLabel;
		JRadioButton tier1Button;
		JRadioButton tier2Button;
		JRadioButton tier3Button;
		JRadioButton tier4Button;
		JLabel businessNameLabel;
		JTextField businessNameField;
		JLabel stateCodeLabel;
		JTextField stateCodeField;
		JLabel cSubmitLabel;
		JButton cSubmitButton;

		ButtonGroup tierGroup;
		JPanel propertyTierPanel;

		String ownerArray [] = {"Owner 1", "Owner 2"};

		ButtonHandler handler;

		public AddCommercialPanel()
		{
			setLayout(new GridLayout(13,2));

			cStreetLabel = new JLabel("Street");
			cStreetField = new JTextField(20);
			cCityLabel = new JLabel("City");
			cCityField = new JTextField(20);
			cStateLabel = new JLabel("State");
			cStateField = new JTextField(20);
			cZipLabel = new JLabel("Zip");
			cZipField = new JTextField(20);
			cSelectOwnerLabel = new JLabel("Select an Owner");
			//selectOwner = new JComboBox<Owner>(ownerArray);
			//selectOwner = new JComboBox<String>(ownerArray);
			cAccountLabel = new JLabel("Account Number");
			cAccountField = new JTextField(20);
			cMarketLabel = new JLabel("Market Value");
			cMarketField = new JTextField(20);
			cDateLabel = new JLabel("Purchase Date");
			cDateField = new JTextField(20);
			cSquareFeetLabel = new JLabel("Square Feet");
			cSquareFeetField = new JTextField(20);
			propertyTierLabel = new JLabel("Property Tier");
			tier1Button = new JRadioButton("TIER 1");
			tier2Button = new JRadioButton("TIER 2");
			tier3Button = new JRadioButton("TIER 3");
			tier4Button = new JRadioButton("TIER 4");
			businessNameLabel = new JLabel("Business Name");	
			businessNameField = new JTextField(20);
			stateCodeLabel = new JLabel("State Code");
			stateCodeField = new JTextField(20);
			cSubmitLabel = new JLabel("Click to Submit");
			cSubmitButton = new JButton("Submit");
			
			propertyTierPanel = new JPanel();
			propertyTierPanel.setLayout(new GridLayout(1,4));
			propertyTierPanel.add(tier1Button);
			propertyTierPanel.add(tier2Button);
			propertyTierPanel.add(tier3Button);
			propertyTierPanel.add(tier4Button);

			tierGroup = new ButtonGroup();
			tierGroup.add(tier1Button);
			tierGroup.add(tier2Button);
			tierGroup.add(tier3Button);
			tierGroup.add(tier4Button);

			add(cStreetLabel);
			add(cStreetField);
			add(cCityLabel);
			add(cCityField);
			add(cStateLabel);
			add(cStateField);
			add(cZipLabel);
			add(cZipField);
			add(cSelectOwnerLabel);
			add(selectOwner);
			add(cAccountLabel);
			add(cAccountField);
			add(cMarketLabel);
			add(cMarketField);
			add(cDateLabel);
			add(cDateField);
			add(cSquareFeetLabel);
			add(cSquareFeetField);
			add(propertyTierLabel);
			add(propertyTierPanel);
			add(businessNameLabel);
			add(businessNameField);
			add(stateCodeLabel);
			add(stateCodeField);
			add(cSubmitLabel);
			add(cSubmitButton);

		ButtonHandler handler = new ButtonHandler();
		cSubmitButton.addActionListener(handler);
			
		} // end constructor

	
		class ButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				String street;
                		String city;
                		String state;
                		int zip;
                		String ownerName;
                		double marketValue;
                		String Date;
                		int squareFeet;
                		Property.PropertyTier propertyTier;
                		boolean floodZone;
                		int accountNumber; 
                		Date purchaseDate;
                		ResidentialProperty.Subdivision subdivision;
                		String businessName;
                		String stateCode;
					
				
				if(ae.getSource()==cSubmitButton)
				{
					
					if(checkCommercialInput())
					{
						
						
						street = cStreetField.getText();
                                        	city = cCityField.getText();
                                        	state = cStateField.getText();
                                        	zip = Integer.parseInt(cZipField.getText());
                                        	ownerName = selectOwner.getSelectedItem().toString();                                       
                                        	accountNumber = Integer.parseInt(cAccountField.getText());
                                        	marketValue = Double.parseDouble(cMarketField.getText());
                                        	Date = cDateField.getText();
                                        	purchaseDate = createDate(Date);
                                        	squareFeet = Integer.parseInt(cSquareFeetField.getText()); 
                                        	propertyTier = determineTier();
                                        	businessName = businessNameField.getText();
                                        	stateCode = stateCodeField.getText();
                                       		propertyList.add(new CommercialProperty(accountNumber,new Address(street,city,state,zip),marketValue,purchaseDate,squareFeet,propertyTier,businessName,stateCode));
                                        
                                        	for(Owner o: ownersList)
                                        	{
                                            		if(ownerName.equals(o.getName()))
                                            		{
                                                		o.addProperty(new CommercialProperty(accountNumber,new Address(street,city,state,zip),marketValue,purchaseDate,squareFeet,propertyTier,businessName,stateCode));
                                            		}
                                        	}   

								

						clearCommercial(); 
					 
					}
				}
			}
		} // end buttonHandler class

		public void checkStateCode(String s) throws StateCodeDoesNotExistException
		{
			 boolean exist = true;
                	 char []values = s.toCharArray();
                	 
                	 if(values.length > 5 || values.length < 0)
                	 {
                		 exist = false;
                	 }
                	 else if(!Character.isLetter(values[0]))
                	 {
                		 exist = false;
                	 }
                	 else if(!Character.isDigit(values[1]))
                	 {
                		 exist = false;
                	 }
                	 else if(!Character.isDigit(values[2]))
                	 {
                		 exist = false;
                	 }
                	 else if(!Character.isDigit(values[3]))
                	 {
                		 exist = false;
                	 }
                	 else if(!Character.isLetter(values[4]))
                	 {
                		 exist = false;
                	 }
                	 
                	 if(!exist)
                		 throw new StateCodeDoesNotExistException();
                

		}

		public boolean checkCommercialInput()
		{
			boolean input = true;

			
			if(cStreetField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Street");
				cStreetField.requestFocus();
				input = false;
			} 
			
			else if(cCityField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter City");
				cCityField.requestFocus();
				input = false;
			} 

			else if(cStateField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter State");
				cStateField.requestFocus();
				input = false;
			} 
			
			else if(cZipField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Zip Code");
				cZipField.requestFocus();
				input = false;
			} 
				
			else if(selectOwner.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(null,"Please Select an Owner");
				input = false;
			}
		

			else if (cAccountField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Account Number");
				cAccountField.requestFocus();
				input = false;
			}

			
			else if (cMarketField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Market Value");
				cMarketField.requestFocus();
				input = false;

			}

			
			else if (cDateField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Purchase Date");
				cDateField.requestFocus();
				input = false;
			}

			
			else if (cSquareFeetField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter Square Feet");
				cSquareFeetField.requestFocus();
				input = false;

			}
		
			
			else if(!tier1Button.isSelected() && !tier2Button.isSelected() && !tier3Button.isSelected() && !tier4Button.isSelected())
			{
				JOptionPane.showMessageDialog(null, "Please Select TIER");
				input = false;
			}


			else if(businessNameField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter a Business Name");
				businessNameField.requestFocus();
				input = false;
			}

			else if(stateCodeField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Enter a State Code");
				stateCodeField.requestFocus();
				input = false;
			}

			else if(!checkDate())
            		{
                		JOptionPane.showMessageDialog(null, "Please enter a valid purchase date in the following format: xx/xx/xx");
                		cDateField.setText("");
                		cDateField.requestFocus();
                		input = false;
            		}
			
			else if(!checkNumberInput())
			{
				input = false;
			}
			else if(true)
			{
				try
				{
					checkStateCode(stateCodeField.getText());
				}
				catch(StateCodeDoesNotExistException scdne)
				{
					JOptionPane.showMessageDialog(null, scdne.getMessage());
					stateCodeField.setText("");
					stateCodeField.requestFocus();
					input = false;
				}
			
			
				return input;
		   }
	
			

		        
			return input;	
		}

		public void clearCommercial()		
		{
			cStreetField.setText("");
			cCityField.setText("");
			cStateField.setText("");
			cZipField.setText("");
			selectOwner.setSelectedIndex(0);
			cAccountField.setText("");
			cMarketField.setText("");
			cDateField.setText("");
			cSquareFeetField.setText("");
			tierGroup.clearSelection();
			businessNameField.setText("");
			stateCodeField.setText("");
			
		}
		
		public boolean checkNumberInput()
		{

			try
			{
				Integer.parseInt(cZipField.getText());
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null,"Please Enter an Integer for Zip Code");
				cZipField.setText("");
				cZipField.requestFocus();
				return false;
			}



			try
			{
				Integer.parseInt(cAccountField.getText());
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null,"Please Enter an Integer for Account Number");
				cAccountField.setText("");
				cAccountField.requestFocus();
				return false;
			}


			try
			{
				Double.parseDouble(cMarketField.getText());
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null,"Enter a Double for Market Value");
				cMarketField.setText("");
				cMarketField.requestFocus();
				return false;
			}
				


			try
			{
				Integer.parseInt(cSquareFeetField.getText());
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null,"Enter an Integer for Square Feet");
				cSquareFeetField.setText("");
				cSquareFeetField.requestFocus();
				return false;
								
			}


			return true;
	
		}


		public boolean checkDate()
		{
			boolean input = true;
			String line = cDateField.getText();
			String [] values = line.split("/");
		
			if(values.length >3)
			{
				input = false;
			}
			else if(values[0].length() != 2)
            		{
                		input = false;
            		}
            		else if(values[1].length() != 2)
            		{
                		input = false;
            		}
            		else if(values[2].length() != 2)
            		{
                		input = false;
            		}
            
            			return input;
        	}

		public Date createDate(String d)
        	{
            		String[]values;
            
            		values = d.split("/");
            
            		return(new Date(Integer.parseInt(values[0]),Integer.parseInt(values[1]), Integer.parseInt(values[2])));           
        	}		

		public Property.PropertyTier determineTier()
        	{
            		Property.PropertyTier propertyTier;
            
            		if(tier1Button.isSelected())
            		{
                		propertyTier = Property.PropertyTier.TIER1;
            		}
            		else if(tier2Button.isSelected())                            
            		{
                		propertyTier = Property.PropertyTier.TIER2;
            		}
            		else if(tier3Button.isSelected())
            		{
                		propertyTier = Property.PropertyTier.TIER3;
            		}
            		else
            		{
                		propertyTier = Property.PropertyTier.TIER4;
            		}
            
            		return propertyTier;
                                        
        	}

		


	} // end class AddCommercialPanel

}






			
