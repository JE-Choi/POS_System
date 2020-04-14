package pos.presentaionLayer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pos.domainLayer.CompositeBestForCustomerPricingStrategy;
import pos.domainLayer.CompositeBestForStorePricingStrategy;
import pos.domainLayer.ISalePricingStrategy;
import pos.domainLayer.ITaxCalculatorAdapter;
import pos.domainLayer.ItemID;
import pos.domainLayer.Money;
import pos.domainLayer.PropertyListener;
import pos.domainLayer.Register;
import pos.domainLayer.Sale;
import pos.domainLayer.ServicesFactory;

public class ProcessSaleJFrame extends JFrame implements PropertyListener{

	private JButton b_makeNewSale,b_enterItem,b_endSale,b_makePayment,b_calculateTax,b_applyDiscount;
	private JTextField t_quentity,t_taxTotal,t_amount,t_balance,t_desc,t_curTotal,t_discountTotal;
	private JPanel p_item,p_quantity,p_desc,p_taxTotal,p_amount,p_balance,p_left,p_right,p_curTotal,p_tax,p_discount,p_discountTotal;
	private JRadioButton r_taxMaster, r_goodAsGoldTaxPro,r_bestForCustomer,r_bestForStore,r_taxNon,r_bestNon;
	private ButtonGroup g_taxGroup,g_discountStragyGroup;
	private JComboBox<String> c_productsJComboBox;	
	private JTextArea View;
	
	// ������ ���� ����
	private Register mRegister;
	private Sale sale;

	// register�� ���� �ϱ� ���ؼ� . ��Ʈ�ѷ��� ���ڷ� ����. 
	public ProcessSaleJFrame(Register register){ 
		super("POS System (�й�: 20161066 �̸�: ������)"); 
		
		mRegister = register; //register�� ����.
		
		// ȭ�� �����ϴ� �Լ�
		buildGUI();
		
		// GUI�� �̺�Ʈ ����
		registerEventHandler();
	
		this.setSize(700, 720);
		this.setVisible(true); // �����ֱ�
		
		//���� ó��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
	// ȭ�� �����ϴ� �Լ�
	private void buildGUI() {
		Container cp = this.getContentPane();
		p_left = new JPanel();
		p_left.setBorder(BorderFactory.createEmptyBorder(25 , 25 , 0 , 0)); // padding ����

		p_right = new JPanel();
		p_right.setBorder(BorderFactory.createEmptyBorder(22 , 22 , 10 , 10)); // padding ����
		
		View = new JTextArea(34,25);
		JScrollPane scrollView = new JScrollPane(View);
		p_right.add(scrollView);
	
		Color btnBackground = new Color(12,176,94);
		
		
		//size
		int b_width = 240;
		int b_height = 35;
		
		int l_width = 120;
		int l_height = 20;
		
		// panel
		p_item = new JPanel();
		
		p_quantity = new JPanel();
		p_taxTotal = new JPanel();
	
		p_amount = new JPanel();
		p_balance = new JPanel();
		p_curTotal= new JPanel();
		p_tax = new JPanel();
		p_discount = new JPanel();
		p_discountTotal = new JPanel();
		
		// �Ǹ� ó�� ���� ��ư
		b_makeNewSale = new JButton("1. makeNewSale( )");
		b_makeNewSale.setPreferredSize(new Dimension(b_width, b_height));
	
		JLabel l_item_id = new JLabel("Item ID : ");
		l_item_id.setPreferredSize(new Dimension(l_width, l_height));

		p_item.add(l_item_id);
		
		JLabel l_quentity = new JLabel("Quentity : ");
		l_quentity.setPreferredSize(new Dimension(l_width, l_height));
		t_quentity =  new JTextField(10);
		p_quantity.add(l_quentity);
		p_quantity.add(t_quentity);

		p_desc = new JPanel();
		JLabel l_desc = new JLabel("Description : ");
		l_desc.setPreferredSize(new Dimension(l_width, l_height));
		t_desc = new JTextField(10);
		p_desc.add(l_desc);
		p_desc.add(t_desc);
		
		b_enterItem = new JButton("2. enterItem( )(�ݺ�)");
		b_enterItem.setPreferredSize(new Dimension(b_width, b_height));

		JLabel l_curTotal = new JLabel("Current Total : ");
		l_curTotal.setPreferredSize(new Dimension(l_width, l_height));
		t_curTotal  = new JTextField(10);
		p_curTotal.add(l_curTotal);
		p_curTotal.add(t_curTotal);
	
		
		b_endSale = new JButton("3.endSale( )");
		b_endSale.setPreferredSize(new Dimension(b_width, b_height));
		
		r_taxMaster = new JRadioButton("TaxMaster");
		r_goodAsGoldTaxPro = new JRadioButton("GoodAsGoldTaxPro");
		r_taxNon = new JRadioButton("taxNon");
		g_taxGroup = new ButtonGroup();
		g_taxGroup.add(r_taxMaster);
		g_taxGroup.add(r_goodAsGoldTaxPro);
		g_taxGroup.add(r_taxNon);
		p_tax.add(r_taxMaster);
		p_tax.add(r_goodAsGoldTaxPro);
		
		b_calculateTax = new JButton("4. calculateTax( )");
		b_calculateTax.setPreferredSize(new Dimension(b_width, b_height));
		
		JLabel l_total = new JLabel("Total with Tax : ");
		l_total.setPreferredSize(new Dimension(l_width, l_height));
		t_taxTotal =new JTextField(10);
		p_taxTotal.add(l_total);
		p_taxTotal.add(t_taxTotal);
		
		g_discountStragyGroup = new ButtonGroup();
		r_bestForCustomer = new JRadioButton("Best For Customer");
		r_bestForStore = new JRadioButton("Best For Store");
		r_bestNon = new JRadioButton("bestNon");
		g_discountStragyGroup.add(r_bestForCustomer);
		g_discountStragyGroup.add(r_bestForStore);
		g_discountStragyGroup.add(r_bestNon);
		p_discount.add(r_bestForCustomer);
		p_discount.add(r_bestForStore);
		
		b_applyDiscount = new JButton("5. applyDiscount( )");
		b_applyDiscount.setPreferredSize(new Dimension(b_width, b_height));
		
		JLabel l_discountTotal = new JLabel("Total after Discount : ");
		t_discountTotal= new JTextField(10);
		p_discountTotal.add(l_discountTotal);
		p_discountTotal.add(t_discountTotal);
		
		JLabel l_amount = new JLabel("Amount : ");
		l_amount.setPreferredSize(new Dimension(l_width, l_height));
		t_amount = new JTextField(10);
		p_amount.add(l_amount);
		p_amount.add(t_amount);
		
		b_makePayment = new JButton("6. makePayment( )");
		b_makePayment.setPreferredSize(new Dimension(b_width, b_height));
		
		JLabel l_balance = new JLabel("Balance : ");
		t_balance = new JTextField(10);
		p_balance.add(l_balance);
		p_balance.add(t_balance);
		l_balance.setPreferredSize(new Dimension(l_width, l_height));
		
		// �޺��ڽ� �߰�
		c_productsJComboBox = new JComboBox<String>();
		c_productsJComboBox.setPreferredSize(new Dimension(110, 25));
		c_productsJComboBox.addItem( "" );
		c_productsJComboBox.setSelectedIndex( 0 );
	
	     // ������ �ҷ��ͼ� �޺��ڽ��� �ֱ�
		 String[] items = (String[]) mRegister.loadItemIDs();
		  int i = 0;
		  
		  while(items.length >  i) {
			   c_productsJComboBox.addItem(items[i]+"");
			   i++;
		   }
		  
		 //��ư �� ����
		  b_makeNewSale.setBackground(btnBackground);
		  b_enterItem.setBackground(btnBackground);
		  b_endSale.setBackground(btnBackground);
		  b_makePayment.setBackground(btnBackground);
		  b_calculateTax.setBackground(btnBackground);
		  b_applyDiscount.setBackground(btnBackground);
		  b_makeNewSale.setForeground(Color.WHITE);
		  b_enterItem.setForeground(Color.WHITE);
		  b_endSale.setForeground(Color.WHITE);
		  b_makePayment.setForeground(Color.WHITE);
		  b_calculateTax.setForeground(Color.WHITE);
		  b_applyDiscount.setForeground(Color.WHITE);
		  
	     p_item.add(c_productsJComboBox);
	     
	     cp.setLayout(new GridLayout(0,2));
	     p_left.setLayout(new FlowLayout());
		
	     p_left.add(b_makeNewSale);
	     p_left.add(p_item);
	     p_left.add(p_quantity);
	     p_left.add(p_desc);
		
	     p_left.add(b_enterItem);
	     p_left.add(p_curTotal);
	     p_left.add(b_endSale);
		 p_left.add(p_tax);
		 p_left.add(b_calculateTax);
		 
	     p_left.add(p_taxTotal);
	     p_left.add(p_discount);
	     p_left.add(b_applyDiscount);
	     p_left.add(p_discountTotal);
	     p_left.add(p_amount);
	     p_left.add(b_makePayment);
	     p_left.add(p_balance);
	     
	     cp.add(p_left);
	     cp.add(p_right);
		
	     // component �ʱ�ȭ
	     init();
			
	}
	
	private void registerEventHandler() {
		b_makeNewSale.addActionListener(makeNewSaleHandler);
		b_enterItem.addActionListener(enterItemHandler);
		b_endSale.addActionListener(endSaleHandler);
		b_makePayment.addActionListener(makePaymentHandler);
		b_calculateTax.addActionListener(calculateTaxHandler);
		r_taxMaster.addItemListener(selectTaxCalcHandler);
		r_goodAsGoldTaxPro.addItemListener(selectTaxCalcHandler);
		r_bestForCustomer.addItemListener(selectDiscountStategycHandler);
		r_bestForStore.addItemListener(selectDiscountStategycHandler);
		b_applyDiscount.addActionListener(applyDiscountHandler);
		c_productsJComboBox.addActionListener(comboboxHandler);
	
	}
	
	private ActionListener makeNewSaleHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			sale = mRegister.makeNewSale();
			// ������ ���
			initialize(sale);
		
			t_quentity.setEnabled(true);
			newSaleInit();
			
			b_makeNewSale.setEnabled(false);

			
			String meg = "[�� �ǸŰ� ���۵Ǿ����ϴ�.]";
			View.append(meg+"\n");
			System.out.println("Status: makeNewSale ����");
		}
		
	};
	
	// �̺�Ʈ ������ ����.
	private ActionListener enterItemHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String selection = c_productsJComboBox.getSelectedItem().toString();
			ItemID id = new ItemID(selection);
			int quantity = 0 ;
			try {
				t_quentity.setFocusable(true);
				quantity = Integer.parseInt(t_quentity.getText());
				if(quantity <= 0) {
					JOptionPane.showMessageDialog(null, "������ 1�̻��� ���� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
					t_quentity.setText("");
					t_quentity.requestFocus();
				} else {
					b_endSale.setEnabled(true);
					mRegister.enterItem(id, quantity);
					
					
					// total�� ����
					sale.setTotal(sale.getTotal("curTotal"));
					
					t_desc.setText(mRegister.showDesc(id));
				
					String meg = "[������ �߰��Ǿ����ϴ�.]";
					View.append(meg+"\n");
					System.out.println("Status: enterItem ����");
				}
			
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "item�� �߰��� ���� �Ǿ����ϴ�.\n�������� ���ڸ� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				t_quentity.setText("");
				t_quentity.requestFocus();
			} 
			
			
		}
		
	};
	
	private ActionListener endSaleHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			mRegister.endSale();
			
			t_quentity.setEnabled(false);
			b_enterItem.setEnabled(false);
			b_endSale.setEnabled(false);
			
			t_amount.setEnabled(true);
			b_calculateTax.setEnabled(true);
			c_productsJComboBox.setEnabled(false);
			
			t_quentity.setText("");
			t_desc.setText("");
			c_productsJComboBox.setSelectedIndex(0);
			
			setTaxCalcHandler(true);
			
			String meg = "[�ǸŰ� ����Ǿ����ϴ�.]";
			View.append(meg+"\n");
			System.out.println("Status: endSale ����");
		}
		
	};
	
	private ActionListener makePaymentHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {

				t_amount.setFocusable(true);
				int amount = Integer.parseInt(t_amount.getText());
				
				if(amount < sale.getTotal("other").getAmount()) {
					JOptionPane.showMessageDialog(null, "�Ѿ׺��� ū ���� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
					t_amount.setText("");
					t_amount.requestFocus();
				} else {
					b_makePayment.setEnabled(false);
					t_amount.setEnabled(false);
					mRegister.makePayment(new Money(amount));
					
					t_balance.setText(String.valueOf(sale.getBalance()));
					b_makeNewSale.setEnabled(true);
					

					String meg ="[�ܾ��Դϴ�.]";
					View.append(meg+"\n");
					System.out.println("Status: makePayment ����");	
				}
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "���ڸ� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				t_amount.setText("");
				t_amount.requestFocus();
			}
		}
		
		
	};
	
	private ActionListener calculateTaxHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			if(r_taxNon.isSelected() == true) {
				JOptionPane.showMessageDialog(null, "���� ���⸦ �������ּ���.", "���", JOptionPane.WARNING_MESSAGE);
			} else {
				String meg = "";
				
				mRegister.calculateTax();
				int taxWithTotal = sale.getTotal("other").getAmount();

				 meg ="[������ ���Ե� �����Դϴ�.]";
				 t_taxTotal.setText(String.valueOf(taxWithTotal));
				 
				 
				View.append(meg+"\n");
				System.out.println("Status: calculateTax ����");
				
				b_applyDiscount.setEnabled(true);
				b_calculateTax.setEnabled(false);
				setTaxCalcHandler(false);
				setDiscountHandler(true);
			}
			
		}
		
	};
	
	 
	private ActionListener applyDiscountHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(r_bestNon.isSelected() == true) {
				JOptionPane.showMessageDialog(null, "������å�� �������ּ���.", "���", JOptionPane.WARNING_MESSAGE);
			} else {
				mRegister.applyDiscount();
				Money total = sale.getTotal("other");
				t_discountTotal.setText(total.getAmount()+"");
				
				String meg ="[���ε� �����Դϴ�.]";
				View.append(meg+"\n");
				System.out.println("Status: applyDiscount ����");
				
				b_applyDiscount.setEnabled(false);
				b_makePayment.setEnabled(true);
				setDiscountHandler(false);
			}
			
		}
		
	};
	private ActionListener comboboxHandler  = new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	    	   t_quentity.setText("");
	    	   String selection = c_productsJComboBox.getSelectedItem().toString();
	    	   if(!(selection.equals(""))) {
	    		   b_enterItem.setEnabled(true);
	    		   ItemID id = new ItemID(c_productsJComboBox.getSelectedItem().toString());
		    	    View.append("Description: "+mRegister.showDesc(id)+"\n");
		    		View.append("Price: " + mRegister.showPrice(id)+"\n");   
	    	   }
	       }
	  };



	  private ItemListener selectTaxCalcHandler = new ItemListener(){

		//������ ���� ������ �����ϸ� �ش� ���ݰ��⸦ ���� ����� ��ü ����
		@Override
		public void itemStateChanged(ItemEvent e) {
			 if(e.getStateChange() == ItemEvent.SELECTED)

             {
				   AbstractButton sel = (AbstractButton)e.getItemSelectable();
					
                    if ( sel.getText().equals("TaxMaster") )

                    {
                    	System.setProperty("taxcalculator.class.name", "pos.domainLayer.TaxMasterAdapter");
                    	View.append("[TaxMaster�� ���õǾ����ϴ�.]\n");
                    }

                    else if ( sel.getText().equals( "GoodAsGoldTaxPro" ) )

                    {
                    	System.setProperty("taxcalculator.class.name", "pos.domainLayer.GoodAsGoldTaxProAdapter");
                    	View.append("[GoodAsGoldTaxPro�� ���õǾ����ϴ�.]\n");
                    }
                    ServicesFactory.getInstance().getTaxCalculatorAdapter();
             }
			
		}
		
	};

	private ItemListener selectDiscountStategycHandler = new ItemListener(){

		//������ ������å�� �����ϸ� �ش� ������å�� ���� ����� ��ü ����
		@Override
		public void itemStateChanged(ItemEvent e) {
			 if(e.getStateChange() == ItemEvent.SELECTED)

             {
				   AbstractButton sel = (AbstractButton)e.getItemSelectable();
				   
					
                    if ( sel.getText().equals("Best For Customer") )
                    {
                    	System.setProperty("PricingStrategy.class.name", "pos.domainLayer.CompositeBestForCustomerPricingStrategy");
                    	sale.setDiscountStrategy();
                    	View.append("[BestForCustomer�� ���õǾ����ϴ�.]\n");
                    }

                    else if ( sel.getText().equals( "Best For Store" ) )
                    {
                    	System.setProperty("PricingStrategy.class.name", "pos.domainLayer.CompositeBestForStorePricingStrategy");
                    	sale.setDiscountStrategy();
                    	View.append("[BestForStore�� ���õǾ����ϴ�.]\n");
                    }
             }
			
		}
		
	};
	
	private void setTaxCalcHandler(boolean b_radioTax) {
			r_taxMaster.setEnabled(b_radioTax);
			r_goodAsGoldTaxPro.setEnabled(b_radioTax);
	}
	

	
	private void setDiscountHandler(boolean b_radioDiscount) {	
			r_bestForCustomer .setEnabled(b_radioDiscount);
			r_bestForStore.setEnabled(b_radioDiscount);
	}
	
	
	
	private void newSaleInit() {
		t_quentity.setText("");
		t_taxTotal.setText("");
		t_amount.setText("");
		t_balance.setText("");
		t_desc.setText("");
		t_curTotal.setText("");
		t_discountTotal.setText("");
		c_productsJComboBox.setSelectedIndex(0);
		
		r_taxNon.setSelected(true);
		r_bestNon.setSelected(true);
		c_productsJComboBox.setEnabled(true);
		
		// system �Ӽ��� �ʱ�ȭ
		System.setProperty("PricingStrategy.class.name", "");
		System.setProperty("taxcalculator.class.name", "");
	
	}

	private void init() {
		
		b_makeNewSale.setEnabled(true);
		b_enterItem.setEnabled(false);
		b_endSale.setEnabled(false);
		b_makePayment.setEnabled(false);
		b_calculateTax.setEnabled(false);
		b_applyDiscount.setEnabled(false);

		setTaxCalcHandler(false);
		setDiscountHandler(false);
		c_productsJComboBox.setEnabled(false);
		
		t_quentity.setEnabled(false);
		t_amount.setEnabled(false);
		

		t_taxTotal.setEditable(false);
		t_balance.setEditable(false);
		t_desc.setEditable(false);
		t_curTotal.setEditable(false);
		t_discountTotal.setEditable(false);
		
		
	}
	@Override
	public void onPropertyEvent(Sale source, String name, Money value) {
		if ( name.equals("sale.total") ) {
			t_curTotal.setText( value.toString() );
		}
		t_curTotal.setText( value.getAmount()+"" );
	}
	
	private void initialize( Sale sale ){
		sale.addPropertyListener( this );
	}
}
