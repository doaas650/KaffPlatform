package App;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.Color;
import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import Dialogs.Thanks;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;

public class SearchFrame {

	protected Shell shell;
	private Text nkn;
	private Text text_title;
	private Text text_level;
	private Text text_price;

	public static void main(String[] args) {
		try {
			SearchFrame window = new SearchFrame();
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
	//TestConnection test=new TestConnection();
	 ArrayList<BookInformation> results;
	 int numberOfEntries=0;
	 int currentEntrieIndex=0;
	 BookInformation currentEnrty;
	 private Text text_type;
	 private Text text_edition;
	 int index=0;

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\KaffPlatformheader.jpg"));
		shell.setText("\u0645\u0639\u0644\u0648\u0645\u0627\u062A \u0627\u0644\u0643\u062A\u0627\u0628");
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		shell.setSize(582, 577);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Thanks thanks=new Thanks();
				shell.close();
				thanks.open();
			}
		});
		button_2.setText("خـــروج");
		button_2.setImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\signout small.png"));
		button_2.setBounds(0, 23, 46, 40);
		
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.setFont(SWTResourceManager.getFont("Arial (Arabic)", 11, SWT.NORMAL));
	
		btnNewButton_3.setBounds(300, 489, 75, 25);
		btnNewButton_3.setText("التالي");
		btnNewButton_3.setEnabled(false);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("Arial (Arabic)", 11, SWT.NORMAL));
		button_1.setBounds(203, 489, 75, 25);
		button_1.setText("السابق");
		button_1.setEnabled(false);

		text_title = new Text(shell, SWT.BORDER | SWT.RIGHT);
		text_title.setEditable(false);
		text_title.setBounds(62, 147, 316, 31);

		text_level = new Text(shell, SWT.BORDER | SWT.RIGHT);
		text_level.setEditable(false);
		text_level.setBounds(312, 205, 66, 25);


		Button button = new Button(shell, SWT.NONE);
		button.setToolTipText("رجــوع");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				MainMenuKaff main = new MainMenuKaff();
				shell.close();
				main.open();

			}
		});
		button.setImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\KaffPlatformheader.jpg"));
		button.setBounds(499, 0, 57, 42);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("Dubai", 14, SWT.BOLD));
		lblNewLabel.setBounds(403, 54, 76, 31);
		lblNewLabel.setText("\u0627\u0644\u0628\u062D\u062B");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(210, 105, 30));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Arial (Arabic)", 20, SWT.NORMAL));
		lblNewLabel_1.setBounds(110, 99, 322, 42);
		lblNewLabel_1.setText(
				"\u0645\u0639\u0644\u0648\u0645\u0640\u0640\u0640\u0640\u0640\u0627\u062A \u0627\u0644\u0643\u062A\u0627\u0628");

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Arial (Arabic)", 16, SWT.NORMAL));
		lblNewLabel_2.setBounds(408, 147, 84, 31);
		lblNewLabel_2.setText(":\u0623\u0633\u0645 \u0627\u0644\u0643\u062A\u0627\u0628");

		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label.setFont(SWTResourceManager.getFont("Arial (Arabic)", 16, SWT.NORMAL));
		label.setBounds(426, 199, 66, 31);
		label.setText(":\u0627\u0644\u0645\u0633\u062A\u0648\u0649");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_1.setFont(SWTResourceManager.getFont("Arial (Arabic)", 14, SWT.NORMAL));
		label_1.setBounds(449, 252, 43, 31);
		label_1.setText(":\u0627\u0644\u062D\u0627\u0644\u0647");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_2.setFont(SWTResourceManager.getFont("Arial (Arabic)", 16, SWT.NORMAL));
		label_2.setBounds(448, 303, 44, 31);
		label_2.setText(":\u0627\u0644\u0633\u0639\u0631");

		nkn = new Text(shell, SWT.BORDER | SWT.RIGHT);
		nkn.setToolTipText("أدخل أسم الكتاب");

		nkn.setForeground(SWTResourceManager.getColor(169, 169, 169));
		nkn.setBounds(62, 58, 296, 25);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Arial (Arabic)", 16, SWT.BOLD));
		btnNewButton.setForeground(SWTResourceManager.getColor(255, 255, 255));
		btnNewButton.setBounds(258, 251, 120, 31);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\KaffPlatformheaderrr.jpg"));
		label_3.setBackgroundImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\KaffPlatformheade.jpeg"));
		label_3.setBounds(0, 0, 566, 48);

		text_price = new Text(shell, SWT.BORDER | SWT.RIGHT);
		text_price.setEditable(false);
		text_price.setBounds(312, 309, 66, 25);
		
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setBackgroundImage(
				SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\Picture\\search-icon-.png"));
		btnNewButton_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		btnNewButton_2.setImage(SWTResourceManager.getImage("C:\\Users\\Doaa\\Pictures\\Picture\\search-icon-.png"));

		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				

				
				try {
					Database.openConnection();
					
					String selectQuery="select bookTitle , bookLevel,bookType,bookPrice, available, edition from book where bookTitle= ?";
					PreparedStatement state=Database.getConnection().prepareStatement(selectQuery);
					BookInformation book = new BookInformation();
					results= book.getTitle(nkn.getText());
					numberOfEntries=results.size();
					
					
				if (numberOfEntries!=0) {
					
						currentEntrieIndex=index;
						currentEnrty=results.get(currentEntrieIndex);
						
						text_title.setText(currentEnrty.BooksTitle);
						text_level.setText(""+currentEnrty.level);
						if(currentEnrty.state) {
							btnNewButton.setBackground(SWTResourceManager.getColor(154, 205, 50));
							btnNewButton.setText("متوفـــــر");
						}
						else {
							btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
							btnNewButton.setText("غيــر متوفــــر");
						}
						text_price.setText(""+currentEnrty.price);
						text_type.setText(currentEnrty.For);
						text_edition.setText(currentEnrty.edition);
						btnNewButton_3.setEnabled(true);
						button_1.setEnabled(true);
						
						
						
					}
					else {
						Error error=new Error();
						error.open();
						
					}
						
						
						
						 
					
					
					
						
					

					

				} catch (SQLException e1) {
					System.out.println("Error");
					Error error=new Error();
					error.open();
				e1.printStackTrace();
				}
				
							
			}
		});
		btnNewButton_2.setBounds(353, 58, 25, 25);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Arial (Arabic)", 16, SWT.NORMAL));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setAlignment(SWT.RIGHT);
		label_4.setBounds(426, 410, 66, 38);
		label_4.setText(":الإصدار");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Arial (Arabic)", 16, SWT.NORMAL));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(426, 357, 66, 25);
		label_5.setText(":النوع");
		
		text_type = new Text(shell, SWT.BORDER | SWT.RIGHT);
		text_type.setEditable(false);
		text_type.setBounds(312, 361, 66, 25);
		
		text_edition = new Text(shell, SWT.BORDER | SWT.RIGHT);
		text_edition.setEditable(false);
		text_edition.setBounds(311, 414, 66, 25);
	}
}
