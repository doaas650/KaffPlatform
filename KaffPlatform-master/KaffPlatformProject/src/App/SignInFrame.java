package App;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.MatteBorder;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import javafx.scene.control.PasswordField;

public class SignInFrame {

	protected Shell shell;
	private Text usernameTxt;
	private Text passwordValue;
	/**
	 * @wbp.nonvisual location=138,113
	 */

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SignInFrame window = new SignInFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	Signin si;
	boolean isCorrect;
	// int numberOfEntries=0;
	 int currentEntrieIndex=0;

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(603, 443);
		shell.setText("تسجيل الدخول");
		
		
		Label signInLabel = new Label(shell, SWT.CENTER);
		signInLabel.setForeground(SWTResourceManager.getColor(210, 105, 30));
		signInLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		signInLabel.setFont(SWTResourceManager.getFont("B Badr", 14, SWT.NORMAL));
		signInLabel.setBounds(111, 113, 347, 34);
		signInLabel.setText("..سجلي دخولك من هنا..");
		
		usernameTxt = new Text(shell, SWT.BORDER);
		usernameTxt.setBounds(111, 164, 247, 24);
		
		Label usernameLabel = new Label(shell, SWT.NONE);
		usernameLabel.setAlignment(SWT.RIGHT);
		usernameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		usernameLabel.setFont(SWTResourceManager.getFont("B Badr", 10, SWT.NORMAL));
		usernameLabel.setBounds(364, 167, 94, 24);
		usernameLabel.setText("اسم المستخدم");
		
		Label passwordLabel = new Label(shell, SWT.NONE);
		passwordLabel.setAlignment(SWT.RIGHT);
		passwordLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		passwordLabel.setFont(SWTResourceManager.getFont("B Badr", 10, SWT.NORMAL));
		passwordLabel.setBounds(364, 206, 94, 24);
		passwordLabel.setText("الرقم السري");
		
		passwordValue = new Text(shell, SWT.BORDER);
		
		passwordValue.setBounds(111, 206, 247, 24);
		
		Button button = new Button(shell, SWT.NONE);
		button.setToolTipText("رجــوع");

		button.addSelectionListener(new SelectionAdapter() {

			@Override

			public void widgetSelected(SelectionEvent e) {

			//pressing the logo will take you to main menu, here it is sign in or search menu

				MainMenuKaff mainSearchOrSignIn = new MainMenuKaff();
				shell.close();

				mainSearchOrSignIn.open();

			

			}

		});
		//TestConnection test=new TestConnection();

		button.setImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\KaffPlatformheader.jpg"));

		button.setBounds(500, 0, 58, 43);
		button.setBackgroundImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\KaffPlatformheader.jpg"));
		
		Button signInButton = new Button(shell, SWT.NONE);
		signInButton.addSelectionListener(new SelectionAdapter() {

			@Override

			public void widgetSelected(SelectionEvent e) {
//check if username and password are correct, then show admin menu or error message
				String username=usernameTxt.getText();
				String password=passwordValue.getText();
				Signin signin= new Signin(username, password);
				String signinQuery="select adminID , adminPassword from kaff.admin where adminID= ? and adminPassword=?";
				try {
					Database.openConnection();
					String selectQuery="select bookTitle , bookLevel,bookType,bookPrice, available, edition from book where bookTitle= ?";
				
				PreparedStatement sign=Database.getConnection().prepareStatement(signinQuery);
				
				
				sign.setString(1, username);
				sign.setString(2,password);
				sign.executeQuery();
				ResultSet resultSign=sign.getResultSet();
				//ResultSetMetaData metadata=resultSign.getMetaData();
				//int  numberOfEntries=metadata.getColumnCount();
				String IDDB = "";
				String PasswordDB = "";
				while(resultSign.next()) {
				 IDDB =resultSign.getString("adminID");
				 PasswordDB=resultSign.getString("adminPassword");
					System.out.println(IDDB);
					System.out.println(PasswordDB);
				}
				
				if(IDDB.equals(username) && PasswordDB.equals(password)) {
					Combobox mainmenu= new Combobox();
					System.out.println("new main");
					shell.close();
					mainmenu.open();
				}
				else {
					Error error =new Error();
					 error.open();
					 }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//si = new Signin(username1, password2);
				
				/*numberOfEntries=results.size();
				String us=null;
				String pas=null;
				
				if(numberOfEntries!=0) {
					currentEntrieIndex=0;
					si=results.get(currentEntrieIndex);
					 us=si.username;
					 pas=si.password;
				}*/
				
			}
				
			

		});
		signInButton.setFont(SWTResourceManager.getFont("B Badr", 12, SWT.BOLD));
		signInButton.setBounds(111, 260, 85, 26);
		signInButton.setText("دخول");
		
		Label noteLabel = new Label(shell, SWT.NONE);
		noteLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		noteLabel.setFont(SWTResourceManager.getFont("B Badr", 10, SWT.NORMAL));
		noteLabel.setBounds(111, 306, 347, 24);
		noteLabel.setText("في حال نسيتِ الرقم السري، نرجو التواصل مع وحدة الأنشطة في الكلية");
		
		
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\KaffPlatformheaderrr.jpg"));
		lblNewLabel.setBounds(0, 0, 607, 50);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setImage(SWTResourceManager.getImage("C:\\Users\\al5an\\Desktop\\iau\\KaffPlatformheader.jpg"));
	
		
	}
}
