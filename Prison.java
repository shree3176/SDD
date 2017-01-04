

import java.awt.event.ItemEvent;
import java.util.*;
import java.util.Date.*;
import java.util.Vector;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import javax.naming.spi.DirStateFactory.Result;


public class Prison  implements ActionListener {
      String warden_name, warden_address, warden_gender,warden_status, warden_id,warden_Datejoined,remind="" ;
      int  warden_rank;
      String prisoner_name,prisoner_address,prisoner_gender,prisoner_doiday,prisoner_doimonth,prisoner_doiyear,prisoner_dorday,prisoner_dormonth,prisoner_doryear,prisoner_slevel,prisoner_cell,npidate,nprdate,prisoner_status="in prison";
      int flag=0,prisoner_id,npdoi,npmoi,npyoi,npdor,npmor,npyor;
      long pdays_remain;
      JMenuBar menubar;
      JMenu menu_mw, menu_mp;
      JMenuItem wadd,wdelete,wdisplay,wcheck,pAdd,pFind,pRelease,pDelete,pDisplay;
      JDesktopPane desk;
	  JInternalFrame infrmPadd;
      String msg;
      JLabel head, lblwAddress,lblwGender,lblWrank,lblWdate_joined,lblWname,lblWname_c,lblwGender_c,lblwAddress_c,lblWrank_c,lblWdate_joined_c,lblWid,lblStart, lblEndDate,lblpgender;//lblWname
      JLabel lblpname,lblpaddress,lblpSecurity,lblpiDate,lblprDate,lblpname_c,lblpiDate_c,lblprDate_c,lblpDaysLeft,lblpDaysLeft_c;
      JTextArea txtAValid;
      JTextField txtwname,txtwAddress,txtWdate_joined,txtpid,txtpname,txtpadd,txtpdate,txtid;//txtwid
      ButtonGroup wrdb,bgPgender,bgpsecurity,bgShare;
      JRadioButton rdbwMale,rdbwFemale,rdbpMale,rdbpFemale,rdbHigh,rdbLow, rdbYes,rdbNo;
      JComboBox cmbRank,cmbSecurity,cmb_wid,cmbpdoi,cmbpmoi,cmbpyoi,cmbpdor,cmbpmor,cmbpyor,cmbpid;
      JButton btnSave,btnReset,btnwDelete,btnpSave,btnpReset,btnpDelete,btnpRelease;
	  //DbConnect db = null;
      Prison()
        {
		   DbConnect db = new DbConnect();
           JFrame frame = new JFrame("Prison Management System");
           frame.setBounds(350,100,600,570);
		   desk=new JDesktopPane();


//                   JInternalFrame infrmSign=new JInternalFrame("Log IN");
//                   infrmSign.setLayout(null);
//                   infrmSign.setVisible(true);
//                   desk.add(infrmSign);
//                   JLabel lbllogin=new JLabel("fgdfgg");
//                   infrmSign.add(lbllogin);

           menubar = new JMenuBar();
           menu_mw = new JMenu("Warden Management     ");
           menu_mp = new JMenu("Prisoner Management  ");
           wadd = new JMenuItem("Add new");
           wdelete = new JMenuItem("Delete");
           wdisplay = new JMenuItem("Display all");
           wcheck = new JMenuItem("Check ratio");
           pAdd = new JMenuItem("Add");
           pFind = new JMenuItem("Find & Display");
           pRelease = new JMenuItem("Release");
           pDelete = new JMenuItem("Delete");
           pDisplay = new JMenuItem("Display All");

           frame.setJMenuBar(menubar);
          menu_mp.add(pAdd);
          menu_mp.add(pFind);
          menu_mp.add(pRelease);
          menu_mp.add(pDelete);
          menu_mp.add(pDisplay);
          menubar.add(menu_mw);
          menubar.add(menu_mp);

          frame.setVisible(true);

          frame.add(desk);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          menu_mw.setMnemonic('W');
          menu_mw.add(new JSeparator());
          menu_mp.setMnemonic('P');
        //  menu_mp.add(new JSeparator());
          pRelease.setEnabled(false);
          pDelete.setEnabled(false);
          menu_mw.add(wadd);
          menu_mw.add(wdelete);
          menu_mw.add(wdisplay);
          menu_mw.add(wcheck);

          wadd.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){

                                      JInternalFrame inframe=new JInternalFrame("Add new Warden");
                                      inframe.setLayout(null);
                                      inframe.setBackground(Color.WHITE);
//                                      Rectangle r = inframe.getBounds();
//                                      inframe.setBounds((int)(r.getX()+r.getWidth()/2-125),(int)(r.getY()+r.getHeight()/2-50),250,100);
                                      JPanel mainpanel=new JPanel();
                                      mainpanel.setBackground(Color.WHITE);
                                      inframe.add(mainpanel);
                                    
                                      lblWname=new JLabel("Warden Name");
                                      lblWname.setBounds(160,50,100,30);
                                      inframe.add(lblWname);

                                      txtwname = new JTextField();
                                      txtwname.setBounds(300, 55, 150,20);
                                      inframe.add(txtwname);

                                       lblwAddress=new JLabel("Address ");
                                      lblwAddress.setBounds(160, 100, 100, 30);
                                      inframe.add(lblwAddress);

                                      txtwAddress=new JTextField();
                                      txtwAddress.setBounds(300, 100,150, 20);
                                      inframe.add(txtwAddress);

                                       lblwGender =new JLabel("Gender");
                                      lblwGender.setBounds(160, 150, 100, 30);
                                      inframe.add(lblwGender);

                                      wrdb =new ButtonGroup();
                                      wrdb.add(rdbwMale);
                                      wrdb.add(rdbwFemale);

                                      rdbwMale=new JRadioButton("Male");
                                      rdbwMale.setBounds(300, 155, 70, 20);
                                      inframe.add(rdbwMale);

                                      rdbwFemale=new JRadioButton("Female");
                                      rdbwFemale.setBounds(380,155, 70, 20);
                                      inframe.add(rdbwFemale);

                                       lblWrank=new JLabel("Warden Rank:");
                                      lblWrank.setBounds(160,200 , 100,30);
                                      inframe.add(lblWrank);

                                      cmbRank = new JComboBox();
                                      inframe.add(cmbRank);
                                      cmbRank.setBounds(300, 200, 150, 20);
                                      cmbRank.addItem("-------Select-------");
                                      cmbRank.addItem("1");
                                      cmbRank.addItem("2");
                                      cmbRank.addItem("3");
                                      cmbRank.addItem("4");
                                      cmbRank.addItem("5");

                                      lblWdate_joined=new JLabel("Date Joined");
                                      lblWdate_joined.setBounds(160, 250,100,30 );
                                      inframe.add(lblWdate_joined);

                                      txtWdate_joined=new JTextField("");
                                      txtWdate_joined.setBounds(300,255,150,20);
                                      inframe.add(txtWdate_joined);

                                      txtAValid=new JTextArea("* All fields are Mandatory.");
                                      txtAValid.setBounds(20,20,300,30);
                                      txtAValid.setBackground(Color.WHITE);
                                      txtAValid.setEditable(false);
                                      txtAValid.setForeground(Color.RED);
                                      txtAValid.setVisible(true);
                                      inframe.add(txtAValid);


                                      btnSave=new JButton("Save");
                                      btnSave.setBounds(160, 330, 70, 30);
                                      inframe.add(btnSave);

                                      inframe.setBounds(0,0,730,570);
                                      desk.add(inframe);
                                      inframe.setVisible(true);

                                      btnReset=new JButton("Reset"); 
                                      btnReset.setBounds(270, 330, 70, 30);
                                      inframe.add(btnReset);

                                      JButton btnExit=new JButton("Exit");
                                      btnExit.setBounds(380, 330, 70, 30);
                                      inframe.add(btnExit);

                                      btnSave.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
				      flag=0;
				      String check="";
                                      /** variable to store warden name */
                                      warden_name=txtwname.getText();
				      if(warden_name.equals(""))
                                      {
					 check =check+"" + "\n";
				         flag++;
				       }
					else
				        {
					Pattern p = Pattern.compile("[0-9,&%$#@!()*^]");
					Matcher m = p.matcher(warden_name);
                                        if (m.find())
					{
					  check =check+"Enter letters from a-z only" + "\n";
					  flag++;
					  //JOptionPane.showMessageDialog(null,"Enter valid character","WARNING",JOptionPane.WARNING_MESSAGE);
        				}

					}
                                        warden_address=txtwAddress.getText();
                                        if(warden_address.equals(""))
                                        {
                                          check =check+"Enter Address" + "\n";
                                          flag++;
                                        }
                                        warden_rank= Integer.parseInt((String)cmbRank.getSelectedItem());
                                        if(cmbRank.getSelectedItem()=="--Select---")
                                        {
                                            flag++;
                                             check=check+"Choose Rank"+"\n";
                                        }
                                        /**Validation for selecting gender*/
                                        if(rdbwMale.isSelected()==false&&rdbwFemale.isSelected()==false)
                                        {
                                           flag++;
                                           check=check + "Select Gender" + "\n";
                                         }
                                         if(rdbwMale.isSelected())
                                         {
                                            warden_gender="Male";
                                            System.out.println(warden_gender);
                                         }
                                         else
                                         {
                                            warden_gender="Female";
                                          }
                                           warden_Datejoined=txtWdate_joined.getText();
                                            if(warden_Datejoined.equals(""))
                                             {
                                               check =check+"Enter the Date Joined " + "\n";
                                               flag++;
                                             }
                                           else
                                             {
                                               String pattern = "DD/MM/YY";
                                             SimpleDateFormat sdf = new SimpleDateFormat();
                                             try{
                                                 sdf.applyPattern(pattern);
                                                 System.out.println("right format");
                                                 java.util.Date date =sdf.parse(warden_Datejoined);
                                             }
                                             catch(Exception er)
                                             {
                                                check=check+"Enter date in DD/MM/YY format"+"\n";
                                                flag++;
                                             }
                                             }
                                            warden_status ="IN Prison";
                                           
                                           String nwid=null;
                                           int flag=0;
                                                int counter=flag;
                                                System.out.println(counter);
                                                if (flag==0)
                                                {
                                                try{
                                                    nwid="WRD1200";
                                                                    ResultSet rs1=db.selectData("SELECT * FROM tblWarden");
                                                                    while(rs1.next()){
                                                                    nwid=rs1.getString("warden_id");
                                                                        System.out.println("helloif");
                                                                      }
                                                                     int iwid=Integer.parseInt(nwid.substring(3));
                                                                     iwid++;
                                                                     System.out.println("helloif"+iwid);
                                                                     nwid="WRD"+String.valueOf(iwid);
                                                    }
                                                catch(Exception ef){System.out.println(ef);}
                                                                     
                                                            try
                                                            {
                                                            String sql = "insert into tblWarden values ('"+ nwid + "','" + warden_name +"','"+warden_address+"','"+warden_gender+"','"+warden_rank+"','"+warden_status+"','"+warden_Datejoined+"')";

                                                            JOptionPane.showMessageDialog(null, "Saved Record Successfully. With Warden Id:"+nwid);
                                                            db.insertdata(sql);
                                                            }
                                                            catch (Exception ex) {
                                                            System.out.print(ex);
                                                            }
                                                           }
                                                else
                                                {
                                                    txtAValid.setVisible(true);
                                                    txtAValid.setText(check);
                                                }

                                                                                                                    }
                                                     });

                                      btnReset.addActionListener(new ActionListener() {
                                                                 public void actionPerformed(ActionEvent e) {
                                                              //   txtwid.setText("");
                                                                 txtwname.setText("");
                                                                 txtwAddress.setText("");
                                                                 wrdb.clearSelection();
                                                                 cmbRank.setSelectedItem("-------Select-------");
                                                                 txtWdate_joined.setText(null);
                                                                 txtAValid.setText(null);

                                                                 }
                                                                 });
                                      
                                        }
                                        });
          wdelete.addActionListener(new ActionListener(){
				    public void actionPerformed(ActionEvent ex){
                                      JInternalFrame inframe1=new JInternalFrame("Delete");
                                      inframe1.setLayout(null);
                                      inframe1.setBounds(0,0,750,570);
                                      inframe1.setBackground(Color.WHITE);
                                      desk.add(inframe1);
                                      inframe1.setVisible(true);

                                      lblWid=new JLabel("Select Warden Id to be Deleted");
                                      lblWid.setBounds(80, 50, 200, 30);
                                      inframe1.add(lblWid);

                                      cmb_wid =new JComboBox();
				      cmb_wid.setBounds(300,55, 150,20);
			              cmb_wid.addItem("---select----");
                                      inframe1.add(cmb_wid);
                                           try{
                                                   ResultSet rs=db.selectData("SELECT warden_id FROM tblWarden where warden_status='IN Prison'");
                                                   while(rs.next()){
                                                     String id=rs.getString("warden_id");
                                                     System.out.println("hello..wanna get idds");
                                                     System.out.println(id);
                                                     cmb_wid.addItem(id);

                                                   }
                                                    }
                                      catch(Exception er){
                                      System.out.println(er);
                                      }
                                      cmb_wid.addItemListener(new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                                           String wid= (String) cmb_wid.getSelectedItem();
        if(wid!="---select---")
        {
        try
         {
         ResultSet rs3 = db.selectData("SELECT * FROM tblWarden where warden_id='"+wid+"'");
            if (rs3.next())
            {
              String wdname=rs3.getString("warden_name");
              lblWname_c.setText(wdname);
              String waddrs=rs3.getString("warden_address");
              lblwAddress_c.setText(waddrs);
              String wgen=rs3.getString("warden_gender");
              lblwGender_c.setText(wgen);
              String wdatej=rs3.getString("warden_DateJoined");
              lblWdate_joined_c.setText(wdatej);
              String wrank=rs3.getString("warden_rank");
              lblWrank_c.setText(wrank);
            }
            }
         catch(Exception e4)
         {
           System.out.println(e4);
         }
          }
                    }
                });

                                      lblWname=new JLabel("Warden Name");
                                      lblWname.setBounds(50,100,100,30);
                                      inframe1.add(lblWname);

                                      lblWname_c=new JLabel("copy");
                                      lblWname_c.setBounds(200,100,100,30);
                                      inframe1.add(lblWname_c);

                                      lblwAddress=new JLabel("Address ");
                                      lblwAddress.setBounds(50, 150, 100, 30);
                                      inframe1.add(lblwAddress);

                                      lblwAddress_c=new JLabel("Address ");
                                      lblwAddress_c.setBounds(200, 150, 100, 30);
                                      inframe1.add(lblwAddress_c);

                                      lblwGender =new JLabel("Gender");
                                      lblwGender.setBounds(50, 200, 100, 30);
                                      inframe1.add(lblwGender);

                                      lblwGender_c =new JLabel("Gender");
                                      lblwGender_c.setBounds(200, 200, 100, 30);
                                      inframe1.add(lblwGender_c);

                                      lblWrank=new JLabel("Warden Rank:");
                                      lblWrank.setBounds(350,100 , 100,30);
                                      inframe1.add(lblWrank);

                                      lblWrank_c=new JLabel("Warden Rank:");
                                      lblWrank_c.setBounds(470,100 , 100,30);
                                      inframe1.add(lblWrank_c);

                                      lblWdate_joined=new JLabel("Date Joined");
                                      lblWdate_joined.setBounds(350, 150,100,30 );
                                      inframe1.add(lblWdate_joined);

                                      lblWdate_joined_c=new JLabel("Date Joined");
                                      lblWdate_joined_c.setBounds(470, 150,100,30 );
                                      inframe1.add(lblWdate_joined_c);

                                      btnwDelete=new JButton("Delete");
                                      btnwDelete.setBounds(250, 250, 70,25);
                                      inframe1.add(btnwDelete);
                                      btnwDelete.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             String Wardenid=(String) cmb_wid.getSelectedItem();
                                             try{
                                                  db.insertdata("delete from tblWarden where warden_id='"+Wardenid+"'" );
                                                 JOptionPane.showMessageDialog(null,"successfully deleted");
                                                 cmb_wid.setSelectedItem("---select---");
                                                 cmb_wid.removeItem(Wardenid);

                                                 lblWname_c.setVisible(false);
                                                 lblwAddress_c.setVisible(false);
                                                 lblwGender_c.setVisible(false);
                                                 lblWrank_c.setVisible(false);
                                                 lblWdate_joined_c.setVisible(false);


                                                 remind="select warden id";
                                             }catch(Exception er){ System.out.println(er);}


                                            
                                    cmb_wid.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        String id=(String)cmb_wid.getSelectedItem();
                        if(id!="--select--")
                        {
                          try
                          {
                              ResultSet rs2=db.selectData("SELECT * FROM tblWarden where warden_id='"+id+"'");
                              if (rs2.next()) {
                                  String name=rs2.getString("warden_name");
                                  String rank=rs2.getString("warden_rank");
                                  String doj=rs2.getString("warden_DateJoined");
                                  String address=rs2.getString("warden_address");
                                  String gender=rs2.getString("warden_gender");
                                  System.out.println(name+rank+doj+address+gender);
                                  lblWname_c.setVisible(true);
                                  lblwAddress_c.setVisible(true);
                                  lblWdate_joined_c.setVisible(true);
                                  lblwGender_c.setVisible(true);
                                  lblWrank_c.setVisible(true);
                                  btnwDelete.setVisible(true);}
                            }catch(Exception ed){
                            System.out.println(ed);}
                        }
                        else{
                            lblWname_c.setVisible(false);
                            lblwAddress_c.setVisible(false);
                             lblwGender_c.setVisible(false);
                             lblWrank_c.setVisible(false);
                             lblWdate_joined_c.setVisible(false);}
                         }
                                        });



                                          }
                                                    }  );
              }});
          wdisplay.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e) {
                                      JInternalFrame inframe2=new JInternalFrame("Display All Wardens");
                                      inframe2.setBounds(0,0,600,570);
                                      desk.add(inframe2);
                                      inframe2.setVisible(true);
                                      JPanel mainpanel=new JPanel();
                                      mainpanel.setBackground(Color.PINK);
                                      JLabel head=new JLabel("Display all Wardens");
                                      head.setFont(new Font("Times New Roman",Font.BOLD,24));
                                      head.setBounds(220,5, 250, 50);
                                      //inframe2.add(head);
                                      inframe2.add(mainpanel);
				try{

    ResultSet rs = db.selectData("select * from tblWarden;");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            Vector columns = new Vector(columnCount);

          //store column names

              columns.add("ID");
              columns.add("Name");
              columns.add("Address");
              columns.add("Gender");
              columns.add("Rank");
              columns.add("Status");
              columns.add("Date Joined");

               //System.out.println(ms);

          Vector data = new Vector();
          Vector row;
          while (rs.next()) {

              row = new Vector(columnCount);
                 for(int i=1; i<=columnCount; i++)
                 {
                     row.add(rs.getString(i));
                 }
                 data.add(row);

                //Debugging

            }

            //Display in JTable
           JTable  jtblReqList  = new JTable(data,columns);
           JScrollPane jsp =new JScrollPane(jtblReqList);
           jsp.setForeground(Color.red);
           jsp.setFont(new Font("Times New Roman", Font.PLAIN,12));
           jsp.setBounds(100,200,300,300);
		   jsp.setSize(300,300);
           jtblReqList.setForeground(Color.black);
           jtblReqList.setFont(new Font("Cambria", Font.PLAIN,15));
          inframe2.add(jsp);
        }
        catch(Exception er)

       {
         System.out.println(er);
        }


									  }
                                      });

          wcheck.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                      JInternalFrame inframe2=new JInternalFrame("Check Ratio");
                                      inframe2.setBounds(0,0,750,570);
                                      inframe2.setBackground(Color.WHITE);
                                      desk.add(inframe2);
                                      inframe2.setVisible(true);
                                      
                                                                            }
                                      });
          pAdd.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                       infrmPadd=new JInternalFrame("Add New Prisoner");
                                       infrmPadd.setLayout(null);
                                       infrmPadd.setBackground(Color.WHITE);
									  
			

                                lblpname=new JLabel("Prisoner Name");
				lblpname.setBounds(25,60 , 100,30);
				infrmPadd.add(lblpname);
                                
                                txtpname = new JTextField();
                                txtpname.setBounds(120, 65, 150,20);
                                infrmPadd.add(txtpname);
                                
                                lblpaddress=new JLabel("Address");
				lblpaddress.setBounds(25,100 , 150,30);
                                infrmPadd.add(lblpaddress);

                                txtpadd = new JTextField();
                                txtpadd.setBounds(120, 105, 150,20);
                        	infrmPadd.add(txtpadd);

                                lblpgender = new JLabel("Gender");
				lblpgender.setBounds(25, 140, 150, 30);
                                infrmPadd.add(lblpgender);

                                bgPgender =new ButtonGroup();

                                rdbpMale=new JRadioButton("Male");
                                rdbpMale.setBounds(120, 145, 80, 20);
                                rdbpMale.setBackground(Color.WHITE);
                                infrmPadd.add(rdbpMale);
                                bgPgender.add(rdbpMale);

				 rdbpFemale=new JRadioButton("Female");
                                 rdbpFemale.setBounds(200, 145, 100, 20);
                                 rdbpFemale.setBackground(Color.WHITE);
                                 infrmPadd.add(rdbpFemale);
                                 bgPgender.add(rdbpFemale);

                                 lblpSecurity=new JLabel("Security Level ");
                                 lblpSecurity.setBounds(300,60, 150,30);
                                 infrmPadd.add(lblpSecurity);
                                 
                                 bgpsecurity=new ButtonGroup();
                                 
                                 rdbHigh=new JRadioButton("High");
                                 rdbHigh.setBounds(400,65,50,20);
                                 rdbHigh.setBackground(Color.WHITE);
                                 infrmPadd.add(rdbHigh);
                                 bgpsecurity.add(rdbHigh);
                                 
                                 rdbLow=new JRadioButton("Low");
                                 rdbLow.setBounds(470,65,50,20);
                                 rdbLow.setBackground(Color.WHITE);
                                 infrmPadd.add(rdbLow);
                                 bgpsecurity.add(rdbLow);

                                 lblpiDate=new JLabel("Imprisoned Date");
                                 lblpiDate.setBounds(300, 100, 150, 30);
                                 infrmPadd.add(lblpiDate);

                                 String[] days={"DD","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                                 cmbpdoi=new JComboBox(days);
                                 cmbpdoi.setBounds(400,105,50,20);
                                 infrmPadd.add(cmbpdoi);

                                 String[] month={"MM","1","2","3","4","5","6","7","8","9","10","11","12"};
                                 cmbpmoi=new JComboBox(month);
                                 cmbpmoi.setBounds(460,105,50,20);
                                 infrmPadd.add(cmbpmoi);

                                 String[] year={"YYYY","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
                                 cmbpyoi=new JComboBox(year);
                                 cmbpyoi.setBounds(520,105,55,20);
                                 infrmPadd.add(cmbpyoi);

                                 lblprDate=new JLabel("Release Date");
                                 lblprDate.setBounds(300,140,150,30);
                                 infrmPadd.add(lblprDate);

                                 cmbpdor=new JComboBox(days);
                                 cmbpdor.setBounds(400,145,50,20);
                                 infrmPadd.add(cmbpdor);

                                 cmbpmor=new JComboBox(month);
                                 cmbpmor.setBounds(460,145,50,20);
                                 infrmPadd.add(cmbpmor);

                                 cmbpyor=new JComboBox(year);
                                 cmbpyor.setBounds(520,145,55,20);
                                 infrmPadd.add(cmbpyor);
 
                                 JLabel lblShare=new JLabel("Shares cell ? ");
                                 lblShare.setBounds(300,180, 150, 30);
                                 infrmPadd.add(lblShare);

                                 bgShare=new ButtonGroup();

                                  rdbYes=new JRadioButton("Yes");
                                  rdbYes.setBounds(400, 185, 60, 20);
                                  rdbYes.setBackground(Color.WHITE);
                                  infrmPadd.add(rdbYes);
                                  bgShare.add(rdbYes);

                                  rdbNo=new JRadioButton("No");
                                  rdbNo.setBounds(470, 185, 60, 20);
                                  rdbNo.setBackground(Color.WHITE);
                                  bgShare.add(rdbNo);
                                  infrmPadd.add(rdbNo);

                                  btnpSave=new JButton("Save");
                                  btnpSave.setBounds(200,250,140,30);
                                  infrmPadd.add(btnpSave);

                                  btnpReset=new JButton("Reset");
                                  btnpReset.addActionListener(this);
                                  btnpReset.setBounds(370,250,140,30);
                                  infrmPadd.add(btnpReset);

                                  infrmPadd.setBounds(0,0,730,570);
                                  desk.add(infrmPadd);
                                  infrmPadd.setVisible(true);
                                      
                                  btnpSave.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                      String msg="";
                                      int iflag=0;
                                      prisoner_name=txtpname.getText();
                                      if(prisoner_name.equals("")) {
                                         iflag++;
                                         msg =msg+"Enter Name " + "\n";
                                         }
                                      else{
                                         Pattern p = Pattern.compile("[0-9,&%$#@!()*^]");
                                         Matcher m = p.matcher(prisoner_name);
                                         if (m.find()){
                                         msg =msg+"Only letters from a-z are valid" + "\n";
                                         iflag++;}
                                        }
                                      if(rdbpMale.isSelected()==false&&rdbpFemale.isSelected()==false){
                                        iflag++;
                                        msg=msg + "Select gender" + "\n";}

                                      if(rdbpMale.isSelected()==true){
                                         prisoner_gender="Male";
                                       }
                                      if(rdbpFemale.isSelected()==true){
                                        prisoner_gender="Female";
                                        }
                                      System.out.println( prisoner_name);

                                      prisoner_address=txtpadd.getText();
                                      if(prisoner_address.equals("")) {
                                        msg =msg+"Enter Address" + "\n";
                                        iflag++;}
                                      
                                      if(rdbHigh.isSelected()==false&&rdbLow.isSelected()==false){
                                        iflag++;
                                        msg=msg + "Select Security level" + "\n";}

                                      if(rdbHigh.isSelected()==true){
                                        prisoner_slevel="High";
                                       }
                                      if(rdbLow.isSelected()==true){
                                        prisoner_slevel="Low";
                                        }

                                      if(rdbYes.isSelected()==true){
                                        prisoner_cell="Yes";
                                       }
                                      if(rdbNo.isSelected()==true){
                                        prisoner_cell="No";
                                        }

                                      prisoner_doiday=(String)cmbpdoi.getSelectedItem();
                                      prisoner_doimonth= (String)cmbpmoi.getSelectedItem();
                                      prisoner_doiyear=(String)cmbpyoi.getSelectedItem();

                                      if(prisoner_doiday.equals("dd")||prisoner_doimonth.equals("mm")||prisoner_doiyear.equals("yyyy")) {
                                        msg =msg+"Enter date of Prisonment:dd-mm-yyyy" + "\n";
                                        iflag++;}
                                      else{
                                       npdoi=Integer.parseInt(prisoner_doiday);
                                       npmoi=Integer.parseInt(prisoner_doimonth);
                                       npyoi=Integer.parseInt(prisoner_doiyear);

                                       npidate=prisoner_doiday+"-"+prisoner_doimonth+"-"+prisoner_doiyear;
                                       System.out.println(npidate);}
                                       System.out.println( prisoner_address);


                                       prisoner_dorday=(String)cmbpdor.getSelectedItem();
                                      prisoner_dormonth= (String)cmbpmor.getSelectedItem();
                                      prisoner_doryear=(String)cmbpyor.getSelectedItem();

                                      if(prisoner_dorday.equals("dd")||prisoner_dormonth.equals("mm")||prisoner_doryear.equals("yyyy")) {
                                        msg =msg+"Enter date of Prisonment:dd-mm-yyyy" + "\n";
                                        iflag++;}
                                      else{
                                       npdor=Integer.parseInt(prisoner_dorday);
                                       npmor=Integer.parseInt(prisoner_dormonth);
                                       npyor=Integer.parseInt(prisoner_doryear);

                                       nprdate=prisoner_dorday+"-"+prisoner_dormonth+"-"+prisoner_doryear;
                                       System.out.println(nprdate);}
                                       System.out.println( prisoner_address);


                                       //Calendar calen1 = Calendar.getInstance();
                                       //Calendar calen2 = Calendar.getInstance();
                                       Calendar calen1 = new GregorianCalendar();
                                       Calendar calen2 = new GregorianCalendar();

                                       calen1.set(npdoi,npmoi ,npyoi);
                                       calen2.set(npdor,npmor,npyor);


//                                    System.out.println("Days= "+daysBetween(calen1.getTime(),calen2.getTime()));
//
//
                                    long milis1 = calen1.getTimeInMillis();
                                    long milis2 = calen2.getTimeInMillis();

                                    // Calculate difference in milliseconds
                                    long day = milis1 - milis2;

                                    // Calculate difference in seconds
                                    long diffSeconds = day / 1000;

                                    // Calculate difference in minutes
                                    long diffMinutes = day / (60 * 1000);

                                    // Calculate difference in hours
                                    long diffHours = day / (60 * 60 * 1000);

                                    // Calculate difference in days
                                    long diffDays = day / (24 * 60 * 60 * 1000);
                                    pdays_remain=diffDays;

                                    System.out.println("In milliseconds: " + day + " milliseconds.");
                                    System.out.println("In seconds: " + diffSeconds + " seconds.");
                                    System.out.println("In minutes: " + diffMinutes + " minutes.");
                                    System.out.println("In hours: " + diffHours + " hours.");
                                    System.out.println("In days: " + diffDays + " days.");


                                    if (diffDays <0){
                                       iflag++;
                                       msg =msg+"Wrong dates check prisonment and release date" + "\n";}

                                       //for generating id

                                           String npid=null;
                                           int flag=0;
                                                int counter=flag;
                                                System.out.println(counter);
                                                if (flag==0)
                                                {
                                                try{
                                                    npid="P12000";
                                                                    ResultSet rs1=db.selectData("SELECT * FROM tblPrisoners");
                                                                    while(rs1.next()){
                                                                    npid=rs1.getString("prisoner_id");
                                                                        System.out.println("fetch");
                                                                      }
                                                                     int ipid=Integer.parseInt(npid.substring(1));
                                                                     ipid++;
                                                                     System.out.println("helloif"+ipid);
                                                                     npid="P"+String.valueOf(ipid);
                                                    }
                                                catch(Exception ef){System.out.println(ef);}


                                        try
                                        {
                                         
                                        System.out.println("reached upto here");
                                        String sql = "insert into tblPrisoners values ('"+ npid + "','" + prisoner_name +"','"+prisoner_address+"','"+prisoner_gender+"','"+npidate+"','"+nprdate+"','"+pdays_remain+"','"+prisoner_slevel+"','"+prisoner_cell+"','in')";
                                        System.out.println(pdays_remain);
                                        db.insertdata(sql);
                                        JOptionPane.showMessageDialog(null, "Saved Record Successfully."+npid);
                                        }
                                        catch (Exception ex) {
                                        System.out.print(ex);
                                        }
                                        }
                             }
                                });



      ; }
      });
          pFind.addActionListener(new ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                      JInternalFrame inframe4=new JInternalFrame("Find & Display Prisoner");
                                      inframe4.setBounds(0,0,730,570);
                                      inframe4.setBackground(Color.white);
                                      inframe4.setLayout(null);
                                      desk.add(inframe4);
                                      inframe4.setVisible(true);

                                    
                                      JLabel lblpId=new JLabel("Prisoner ID No");
                                      lblpId.setBounds(50, 50, 100, 30);
                                      inframe4.add(lblpId);

                                       lblpname=new JLabel("Prisoner Name");
				       lblpname.setBounds(50,100 , 100,30);
				       inframe4.add(lblpname);

                                       lblpname_c=new JLabel("djd");
                                       lblpname_c.setBounds(200,100,100,30);
                                       inframe4.add(lblpname_c);

                                       lblpiDate=new JLabel("Imprisoned Date");
                                       lblpiDate.setBounds(50, 150, 150, 30);
                                       inframe4.add(lblpiDate);

                                       lblpiDate_c=new JLabel("idate");
                                       lblpiDate_c.setBounds(200, 150, 150, 30);
                                       inframe4.add(lblpiDate_c);

                                  lblprDate=new JLabel("Release Date");
                                 lblprDate.setBounds(50,200,150,30);
                                 inframe4.add(lblprDate);

                                 lblprDate_c=new JLabel("rd");
                                 lblprDate_c.setBounds(200,200,150,30);
                                 inframe4.add(lblprDate_c);

                                 lblpDaysLeft=new JLabel("No Of Days Left");
                                 lblpDaysLeft.setBounds(50,250,150,30);
                                 inframe4.add(lblpDaysLeft);

                                 lblpDaysLeft_c=new JLabel("No Of Days Left_c");
                                 lblpDaysLeft_c.setBounds(200,250,150,30);
                                 inframe4.add(lblpDaysLeft_c);

                                 btnpDelete =new JButton("Delete");
                                 btnpDelete.setBounds(50,300,100,30);
                                 inframe4.add(btnpDelete);

                                  btnpRelease =new JButton("Release");
                                 btnpRelease.setBounds(200,300,100,30);
                                 inframe4.add(btnpRelease);
                                 btnpRelease.setEnabled(false);



                                      cmbpid=new JComboBox();
                                      cmbpid.setBounds(200, 60, 70, 20);
                                      cmbpid.addItem("---select id---");
                                      inframe4.add(cmbpid);

         try {
            ResultSet rs = db.selectData("SELECT prisoner_id FROM tblPrisoners where prisoner_status='in'");

            while(rs.next())
            {

            String id= rs.getString("prisoner_id");
            System.out.println("hello");
            System.out.println(id);
            cmbpid.addItem(id);
//            String pname=rs.getString("prisoner_name");
//            lblpname_c.setText(pname);
//            System.out.println("got it");
                }
               }
            catch(Exception er)
               {
              System.out.println(er);
            }


                                      cmbpid.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
        String id= (String) cmbpid.getSelectedItem();
        if(id!="---select---")
        {
        try
         {
         ResultSet rs2 = db.selectData("SELECT prisoner_name,prisoner_idate,prisoner_rdate,prisoner_daysremain FROM tblPrisoners where prisoner_id='"+id+"'");
            if (rs2.next())
            {
              String prisname=rs2.getString("prisoner_name");
              lblpname_c.setText(prisname);
              System.out.println("atleast now");
              String pImdate=rs2.getString("prisoner_idate");
              lblpiDate_c.setText(pImdate);
              String pRdate=rs2.getString("prisoner_rdate");
              lblprDate_c.setText(pRdate);
              String pDaysLeft=rs2.getString("prisoner_daysremain");
              lblpDaysLeft_c.setText(pDaysLeft);
            }
            }
         catch(Exception e4)
         {
           System.out.println(e4);
         }
          }
 else
        {

        }

        }
        });

                                    ; }
                                      });


          pRelease.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e) {
                                      JInternalFrame inframe5=new JInternalFrame("Release Prisoner");
                                      inframe5.setBounds(0,0,730,570);
                                      desk.add(inframe5);
                                      inframe5.setVisible(true);

                                      //inframe5.setBackground(Color.CYAN);
                                      JPanel mainpanel=new JPanel();
                                      mainpanel.setBackground(Color.PINK);
                                      JLabel head=new JLabel("Find and Display Prisoner");
                                      head.setFont(new Font("Times New Roman",Font.BOLD,24));
                                      head.setBounds(220,5, 300, 50);
                                      inframe5.add(head);
                                      inframe5.add(mainpanel);}
                                      });

          pDelete.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                      JInternalFrame inframe6=new JInternalFrame("Delete Prisoner");
                                      inframe6.setBounds(0,0,730,570);
                                      desk.add(inframe6);
                                      inframe6.setVisible(true);

                                  //  inframe6.setBackground(Color.CYAN);
                                      JPanel mainpanel=new JPanel();
                                      mainpanel.setBackground(Color.PINK);
                                      JLabel head=new JLabel("Find and Display Prisoner");
                                      head.setFont(new Font("Times New Roman",Font.BOLD,24));
                                      head.setBounds(220,5, 300, 50);
                                      inframe6.add(head);
                                      inframe6.add(mainpanel);}
                                      });
          pDisplay.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e) {
                                      JInternalFrame inframe7=new JInternalFrame("Display");
                                      inframe7.setBackground(Color.WHITE);
                                      inframe7.setBounds(0,0,730,570);
                                      desk.add(inframe7);
                                      inframe7.setVisible(true);
                                      
                                      try {

             ResultSet rs = db.selectData("select * from tblPrisoners;");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            Vector columns = new Vector(columnCount);

          //store column names

              columns.add("ID");
              columns.add("Name");
              columns.add("Address");
              columns.add("Gender");
              columns.add("Date Imprisoned");
              columns.add("Date Released");
              columns.add("Days Remain");
              columns.add("Security level");
              columns.add("Shares Cells?");
              columns.add("Status");

               //System.out.println(ms);

          Vector data = new Vector();
          Vector row;
          while (rs.next()) {

              row = new Vector(columnCount);
                 for(int i=1; i<=columnCount; i++)
                 {
                     row.add(rs.getString(i));
                 }
                 data.add(row);

                //Debugging

            }

            //Display in JTable
           JTable  jtblPrisoners  = new JTable(data,columns);
           JScrollPane jsp =new JScrollPane(jtblPrisoners);
           jsp.setForeground(Color.red);
           jsp.setFont(new Font("Times New Roman", Font.PLAIN,12));
           jsp.setBounds(100,200,300,300);
		   jsp.setSize(300,300);
           jtblPrisoners.setForeground(Color.black);
           jtblPrisoners.setFont(new Font("Cambria", Font.PLAIN,15));
          inframe7.add(jsp);
        }
        catch(Exception er)

       {
         System.out.println(er);
        }

}
                                      });

}
//          public int daysBetween(Date d1, Date d2){
//                                      return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
//                                        }
//

  public void actionPerformed(ActionEvent e) {


	}
//
//public String getwid()
//	{
//      String wid="1200";
//
//
//	     return wid;
//     }






            }
