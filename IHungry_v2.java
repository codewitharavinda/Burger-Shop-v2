import java.util.*; 
class Burger {
	
	final static double BURGERPRICE=500;
	public static final int CANCEL=0;
	public static final int PREPARING=1;
	public static final int DELIVERED=2;
	
	private String orderId;
	private String customerId;
	private String name;
	private int qty;
	private int orderStatus;
	
	public Burger(String orderId,String customerId,String name,int qty,int orderStatus) {
		this.orderId=orderId;
		this.customerId=customerId;
		this.name=name;
		this.qty=qty;
		this.orderStatus=orderStatus;
	}
	
	//getter
	public String getOrderId(){
		return orderId;
	}
	public String getCustomerId(){
		return customerId;
	}
	public String getName(){
		return name;
	}
	public int getQty(){
		return qty;
	}
	public int getOrderStatus(){
		return orderStatus;
	}
	
	//setter
	public void setOrderId(String newOrderId){
		this.orderId=newOrderId;
	}
	public void setCustomerId(String newCustomerId){
		this.customerId=newCustomerId;
	}
	public void setName(String newName){
		this.name=newName;
	}
	public void setQty(int newQty){
		this.qty=newQty;
	}
	public void setOrderstatus(int newOrderStatus){
		this.orderStatus=newOrderStatus;
	}
}

class IHungry_v2 {
	
	
	static Burger[] burger = new Burger[0];
	
	public static boolean isValidOrderId(String orderId){
		if(orderId.length()!=5){
			return false;
		}
		if(orderId.charAt(0)!='B' && orderId.charAt(0)!='b'){
			return false;
		}
		for(int i=1; i<orderId.length(); i++){
			if(orderId.charAt(i)<'0' || orderId.charAt(i)>'9'){
				return false;
			}
		}
		return true;
	}
	
	public static boolean searchName(String[] arr, String customerName){
		for(int i=0; i<arr.length; i++){
			if(arr[i].equalsIgnoreCase(customerName)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean searchId(String[] arr, String customerId){
		for(int i=0; i<arr.length; i++){
			if(arr[i].equalsIgnoreCase(customerId)){
				return true;
			}
		}
		return false;
	}
	
	public static void extendArray(){
		Burger[] tempArray=new Burger[burger.length+1];
		for (int i=0;i<burger.length;i++) {
			tempArray[i]=burger[i];
		}
		burger=tempArray;
		
	}
	
	public static String generateOrderId(){
		int orderCount=burger.length+1;
		String orderId;
		if(orderCount<10){
			orderId="B000"+orderCount;
		}else if(orderCount<100){
			orderId="B00"+orderCount;
		}else if(orderCount<1000){
			orderId="B0"+orderCount;
		}else{
			orderId="B"+orderCount;
		}
		return orderId;
	}
	
	public static String getName(String key){
		for(int i=0; i<burger.length; i++){
			if(burger[i].getCustomerId().equalsIgnoreCase(key)){
				return burger[i].getName();
			}
		}
		return "";
	}
	

	public static String getOrderStatusName(int index){
		if(index==Burger.PREPARING){
			return "Preparing";
		}else if(index==Burger.DELIVERED){
			return "Delivered";
		}else if(index==Burger.CANCEL){
			return "Cancelled";
		}else{
			return "";
		}
	}
	
	public static boolean isValidCustomerId(String customerId){
		if(customerId.length()!=10){
			return false;
		}
		if(customerId.charAt(0)!='0'){
			return false;
		}
		for(int i=0; i<customerId.length(); i++){
			if(customerId.charAt(i)<'0' || customerId.charAt(i)>'9'){
				return false;
			}
		}
		return true;
	}
	
	public static boolean indexOf(String key){
		for(int i=0; i<burger.length; i++){
			if(key.equalsIgnoreCase(burger[i].getCustomerId())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCancelledOrderId(String orderId){
		for(int i=0; i<burger.length; i++){
			if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
				if(burger[i].getOrderStatus()==Burger.CANCEL){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isDeliveredOrderId(String orderId){
		for(int i=0; i<burger.length; i++){
			if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
				if(burger[i].getOrderStatus()==Burger.DELIVERED){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isOrderStatusId(int newOrderId){
		if(newOrderId==Burger.PREPARING || newOrderId==Burger.DELIVERED || newOrderId==Burger.CANCEL){
			return true;
		}
		return false;
	}
	
	public static void mainMenu(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("--------------------------------------------------------------");
			System.out.println("|                       iHungry Burger                       |");
			System.out.println("--------------------------------------------------------------\n\n");
			System.out.println("[1] Place Order		[2] Search Best Customer");
			System.out.println("[3] Search Order	[4] Search Customer");
			System.out.println("[5] View Orders		[6] Update Order Details");
			System.out.println("[7] Exit\n\n");
			
			System.out.print("Enter an option to continue > ");
			int option=input.nextInt();
			
			switch(option){
				case 1 :
					placeOrder();
					break;
				case 2 :
					searchBestCustomer();
					break;
				case 3 :
					searchOrder();
					break;
				case 4 :
					searchCustomer();
					break;
				case 5 :
					viewOrders();
					break;
				case 6 :
					updateOrderDetails();
					break;
				case 7 :
					System.out.println("\n\tTHANK YOU FOR USING iHUNGRY BURGER");
					System.out.println("\t\tHAVE A NICE DAY!!!");
					break L1;
				default :
					System.out.println("\tInvalid option...");
					System.out.print("Do you want to try again? (Y/N) : ");
					String retry=input.next().toUpperCase();
					if(retry.equals("Y")){
						continue L1;
					}else{
						break L1;
					}
			}
		}while(true);
	}
	
		public static void placeOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("-----------------------------------------------------------");
			System.out.println("|                       Place Order                       |");
			System.out.println("-----------------------------------------------------------\n\n");
			
			String newOrderId=generateOrderId();
			System.out.printf("ORDER ID - %s\n", newOrderId);
			System.out.println("================\n\n\n");
			
			System.out.print("Enter Customer ID (phone no.) : ");
			String customerId=input.next();
			while(!isValidCustomerId(customerId)){
				System.out.println("\tInvalid phone number... Try again...!\n");
				System.out.print("Enter Customer ID (phone no.) : ");
				customerId=input.next();
			}
			String index=getName(customerId);
			String customerName="";
			if(index.equals("")){
				System.out.print("Enter Customer Name           : ");
				customerName=input.next();
			}else{
				System.out.printf("Customer Name                 : %s\n",index);
			}
			
			System.out.print("Enter Burger Quantity         - ");
			int qty=input.nextInt();
			
			double total=(double)Burger.BURGERPRICE*qty;
			System.out.printf("Total Value                   - %.2f\n",total);
			
			L2:do{
				System.out.print("\tAre you confirm order - ");
				String confirm=input.next().toLowerCase();
				if(confirm.equals("y")){
					extendArray();
					burger[burger.length-1]=new Burger(newOrderId,customerId,customerName,qty,Burger.PREPARING);
					System.out.println("\n\tYour order is entered to the system successfully...");
					L3:do{
						System.out.print("\nDo you want to place another order (Y/N): ");
						String addNewOrder=input.next().toLowerCase();
						if(addNewOrder.equals("y")){
							continue L1;
						}else if(addNewOrder.equals("n")){
							break L1;
						}else{
							System.out.println("\tWrong option\n");
							continue L3;
						}
					}while(true);
				}else if(confirm.equals("n")){
					L4:do{
						System.out.print("\nDo you want to retry: ");
						String addNewOrder=input.next().toLowerCase();
						if(addNewOrder.equals("y")){
							continue L1;
						}else if(addNewOrder.equals("n")){
							break L1;
						}else{
							System.out.println("\tWrong option\n");
							continue L4;
						}
					}while(true);
				}else{
					System.out.println("\t\tWrong option\n");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void searchBestCustomer(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("-------------------------------------------------------------");
			System.out.println("|                       BEST Customer                       |");
			System.out.println("-------------------------------------------------------------\n\n");
			
			System.out.println("--------------------------------------");
			System.out.printf("%-12s %-15s %7s\n","CustomerID","Name","Total");
			System.out.println("--------------------------------------");
			
			String[] bestCustIdArr=new String[0];
			String[] bestCustNameArr=new String[0];
			double[] bestCustTotalArr=new double[burger.length];
			
			for(int i=0; i<burger.length; i++){
				if(!searchId(bestCustIdArr,burger[i].getCustomerId())){
					String[] tempCustomerIdArr=new String[bestCustIdArr.length+1];
					for(int j=0; j<bestCustIdArr.length; j++){
						tempCustomerIdArr[j]=bestCustIdArr[j];
					}
					bestCustIdArr=tempCustomerIdArr;
					bestCustIdArr[bestCustIdArr.length-1]=burger[i].getCustomerId();
				}
			}
			
			for(int i=0; i<burger.length; i++){
				if(!searchName(bestCustNameArr,burger[i].getName())){
					String[] tempCustomerNameArr=new String[bestCustNameArr.length+1];
					for(int j=0; j<bestCustNameArr.length; j++){
						tempCustomerNameArr[j]=bestCustNameArr[j];
					}
					bestCustNameArr=tempCustomerNameArr;
					bestCustNameArr[bestCustNameArr.length-1]=burger[i].getName();
				}
			}
			
			for(int i=0; i<bestCustIdArr.length; i++){
				double total=0;
				for(int j=0; j<burger.length; j++){
					if(burger[j].getCustomerId().equalsIgnoreCase(bestCustIdArr[i])){
						total+=burger[j].getQty()*Burger.BURGERPRICE;
					}
				}
				bestCustTotalArr[i]=total;
			}
			
			for(int i=0; i<bestCustTotalArr.length-1; i++){
				for(int j=0; j<bestCustTotalArr.length-1; j++){
					if(bestCustTotalArr[j]<bestCustTotalArr[j+1]){
						String tempId=bestCustIdArr[j];
						bestCustIdArr[j]=bestCustIdArr[j+1];
						bestCustIdArr[j+1]=tempId;
						
						double tempTotal=bestCustTotalArr[j];
						bestCustTotalArr[j]=bestCustTotalArr[j+1];
						bestCustTotalArr[j+1]=tempTotal;
						
						String tempName=bestCustNameArr[j];
						bestCustNameArr[j]=bestCustNameArr[j+1];
						bestCustNameArr[j+1]=tempName;
					}
				}
			}
			
			for(int i=0; i<bestCustIdArr.length; i++){
				System.out.printf("%-12s %-15s %8.2f\n",bestCustIdArr[i],bestCustNameArr[i],bestCustTotalArr[i]);
				System.out.println("--------------------------------------");
			}
			
			L2:do{
				System.out.print("\n\tDo you want to go back to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\t\tWrong option\n");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void searchOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("|                       SEARCH ORDER DETAILS                       |");
			System.out.println("--------------------------------------------------------------------\n");
			
			System.out.print("Enter order Id - ");
			String orderId=input.next();
			while(!isValidOrderId(orderId)){
				System.out.println("\tInvalid Order ID...Please try again...\n");
				System.out.print("Enter order Id - ");
				orderId=input.next();
			}
			
			boolean haveDetails=false;
			for(int i=0; i<burger.length; i++){
				if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
					System.out.println("\n-----------------------------------------------------------------------------");
					System.out.printf("%-10s %-12s %-15s %-10s %-12s %-12s|\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue", "Status");
					System.out.println("-----------------------------------------------------------------------------");
					System.out.printf("%-10s %-12s %-15s %-10d %-12.2f %-12s|\n",burger[i].getOrderId(), burger[i].getCustomerId(), burger[i].getName(), burger[i].getQty(), burger[i].getQty()*Burger.BURGERPRICE, getOrderStatusName(burger[i].getOrderStatus()));
					System.out.println("-----------------------------------------------------------------------------");
					haveDetails=true;
				}
			}
			if(!haveDetails){
				L2:do{
					System.out.print("\n\n\nInvalid Order ID. Do you want to enter again? (Y/N)> ");
					String retry=input.next().toLowerCase();
					if(retry.equals("y")){
						continue L1;
					}else if(retry.equals("n")){
						break L1;
					}else{
						System.out.print("Wrong option");
						continue L2;
					}
				}while(true);
			}
			L3:do{
				System.out.print("\nDo you want to search another order details (Y/N)>: ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					continue L1;
				}else if(retry.equals("n")){
					break L1;
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void searchCustomer(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("|                       SEARCH CUSTOMER DETAILS                       |");
			System.out.println("-----------------------------------------------------------------------\n\n");
			
			System.out.print("Enter customer Id - ");
			String customerId=input.next();
			while(!isValidCustomerId(customerId)){
				System.out.println("\tInvalid phone number... Try again...!\n");
				System.out.print("Enter Customer Id (phone no.) : ");
				customerId=input.next();
			}
			
			if(!indexOf(customerId)){
				System.out.println("\n\n\tThis customer ID is not added yet....");
			}else{
				System.out.printf("\n\n\nCustomerID - %s\n",customerId);
				System.out.printf("Name       - %s\n",getName(customerId));
				
				System.out.println("\nCustomer Order details");
				System.out.println("========================\n");
				
				System.out.println("-----------------------------------------");
				System.out.printf(" %-10s %8s %13s \n","Order_ID","Order_Quantity","Total_Value");
				System.out.println("-----------------------------------------");
				for(int i=0; i<burger.length; i++){
					if(customerId.equalsIgnoreCase(burger[i].getCustomerId())){
						System.out.printf(" %-10s %8d %19.2f\n",burger[i].getOrderId(),burger[i].getQty(),burger[i].getQty()*Burger.BURGERPRICE);
						System.out.println("-----------------------------------------");
					}
				}
			}
			L3:do{
				System.out.print("\nDo you want to search another customer details (Y/N)>: ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					continue L1;
				}else if(retry.equals("n")){
					break L1;
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void viewOrders(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("------------------------------------------------------------------");
			System.out.println("|                       VIEW ORDER DETAILS                       |");
			System.out.println("------------------------------------------------------------------\n");
			
			System.out.println("[1] Delivered Order");
			System.out.println("[2] Preparing Order");
			System.out.println("[3] Cancel Order");
			System.out.println("[4] Exit to Main Menu");
			
			System.out.print("\nEnter an option to continue > ");
			int option=input.nextInt();
			
			switch(option){
				case 1 : 
					deliveredOrder();
					break;
				case 2 :
					preparingOrder();
					break;
				case 3 :
					cancelOrder();
					break;
				case 4 :
					break L1;
				default :
					System.out.println("\tInvalid option...");
					System.out.print("Do you want to try again? (Y/N) : ");
					String retry=input.next().toUpperCase();
					if(retry.equals("Y")){
						continue L1;
					}else{
						break L1;
					}
			}
		}while(true);
	}
	
		public static void deliveredOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("---------------------------------------------------------------");
			System.out.println("|                       DELIVERED ORDER                       |");
			System.out.println("---------------------------------------------------------------\n\n");
			
			System.out.println("-------------------------------------------------------------------");
			System.out.printf(" %-10s %-16s %-13s %8s %13s |\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue");
			System.out.println("-------------------------------------------------------------------");
			for(int i=0; i<burger.length; i++){
				if(burger[i].getOrderStatus()==Burger.DELIVERED){
					System.out.printf(" %-10s %-16s %-13s %5d %16.2f |\n",burger[i].getOrderId(), burger[i].getCustomerId(), burger[i].getName(), burger[i].getQty(), burger[i].getQty()*Burger.BURGERPRICE);
					System.out.println("-------------------------------------------------------------------");
				}
			}
			L2:do{
				System.out.print("\nDo you want to go to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\tWrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void preparingOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("---------------------------------------------------------------");
			System.out.println("|                       PREPARING ORDER                       |");
			System.out.println("---------------------------------------------------------------\n\n");
			
			System.out.println("-------------------------------------------------------------------");
			System.out.printf(" %-10s %-16s %-13s %8s %13s |\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue");
			System.out.println("-------------------------------------------------------------------");
			for(int i=0; i<burger.length; i++){
				if(burger[i].getOrderStatus()==Burger.PREPARING){
					System.out.printf(" %-10s %-16s %-13s %5d %16.2f |\n",burger[i].getOrderId(), burger[i].getCustomerId(), burger[i].getName(), burger[i].getQty(), burger[i].getQty()*Burger.BURGERPRICE);
					System.out.println("-------------------------------------------------------------------");
				}
			}
			L2:do{
				System.out.print("\nDo you want to go to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\tWrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void cancelOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("---------------------------------------------------------------");
			System.out.println("|                       CANCELLED ORDER                       |");
			System.out.println("---------------------------------------------------------------\n\n");
			
			System.out.println("-------------------------------------------------------------------");
			System.out.printf(" %-10s %-16s %-13s %8s %13s |\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue");
			System.out.println("-------------------------------------------------------------------");
			for(int i=0; i<burger.length; i++){
				if(burger[i].getOrderStatus()==Burger.CANCEL){
					System.out.printf(" %-10s %-16s %-13s %5d %16.2f |\n",burger[i].getOrderId(), burger[i].getCustomerId(), burger[i].getName(), burger[i].getQty(), burger[i].getQty()*Burger.BURGERPRICE);
					System.out.println("-------------------------------------------------------------------");
				}
			}
			L2:do{
				System.out.print("\nDo you want to go to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\tWrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void updateOrderDetails(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("|                       UPDATE ORDER DETAILS                       |");
			System.out.println("--------------------------------------------------------------------\n\n");
			
			System.out.print("Enter order Id - ");
			String orderId=input.next();
			while(!isValidOrderId(orderId)){
				System.out.println("\tInvalid Order ID...Please try again...\n");
				System.out.print("Enter order Id - ");
				orderId=input.next();
			}
			
			if(isDeliveredOrderId(orderId)){
				System.out.println("\nThe Order is already delivered...You can not update this order...");
			}else if(isCancelledOrderId(orderId)){
				System.out.println("\nThe Order is already cancelled...You can not update this order...");
			}else{
				for(int i=0; i<burger.length; i++){
					if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
						System.out.printf("\nOrderID       - %s\n",burger[i].getOrderId());
						System.out.printf("CustomerID    - %s\n",burger[i].getCustomerId());
						System.out.printf("Name          - %s\n",burger[i].getName());
						System.out.printf("Quantity      - %d\n",burger[i].getQty());
						System.out.printf("OrderValue    - %.2f\n",burger[i].getQty()*Burger.BURGERPRICE);
						System.out.printf("OrderStatus   - %s\n",getOrderStatusName(burger[i].getOrderStatus()));
					}
				}
				
				L2:do{
					System.out.println("\nWhat do you want to update ?");
					System.out.println("\t(01) Quantity");
					System.out.println("\t(02) Status\n");
					
					System.out.print("Enter your option - ");
					int option=input.nextInt();
				
					switch(option){
						case 1 :
							updateQuantity(orderId);
							break;
						case 2 :
							updateStatus(orderId);
							break;
						default :
							System.out.println("\tWrong option...\n");
							continue L2;
				}
				}while(true);
			}
			
			L3:do{
				System.out.print("\n\nDo you want to update another order details (Y/N)>: ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					continue L1;
				}else if(retry.equals("n")){
					break L1;
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void updateQuantity(String orderId){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("Quantity Update");
			System.out.println("================");
			for(int i=0; i<burger.length; i++){
				if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
					System.out.printf("\nOrderID       - %s\n",burger[i].getOrderId());
					System.out.printf("CustomerID    - %s\n",burger[i].getCustomerId());
					System.out.printf("Name          - %s\n\n",burger[i].getName());
					
					L2:while(true){
						System.out.print("Enter your quantity update value - ");
						int qty=input.nextInt();
						if(qty>0){
							burger[i].setQty(qty);
							break L2;
						}else{
							System.out.println("\tInvalid quantity input...Please try again...\n");
							continue L2;
						}
					}
					
					System.out.println("\n\tUpdate order quantity successfully...\n");
					
					System.out.printf("New order quantity - %d\n",burger[i].getQty());
					System.out.printf("New order value - %.2f\n\n",burger[i].getQty()*Burger.BURGERPRICE);
				}
			}
			L3:do{
				System.out.print("Do you want to update another order details (Y/N): ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					updateOrderDetails();
				}else if(retry.equals("n")){
					mainMenu();
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void updateStatus(String orderId){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("Status Update");
			System.out.println("================");
			for(int i=0; i<burger.length; i++){
				if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
					System.out.printf("\nOrderID       - %s\n",burger[i].getOrderId());
					System.out.printf("CustomerID    - %s\n",burger[i].getCustomerId());
					System.out.printf("Name          - %s\n\n",burger[i].getName());
					
					System.out.println("\t(0)Preparing");
					System.out.println("\t(1)Delivered");
					System.out.println("\t(2)Cancel");
					
					System.out.print("\nEnter new order status - ");
					int newOrderId=input.nextInt();
					while(!isOrderStatusId(newOrderId)){
						System.out.println("\tInvalid status input...Please try again...");
						System.out.print("\nEnter new order status - ");
						newOrderId=input.nextInt();
					}
					burger[i].setOrderstatus(newOrderId);
					
					System.out.println("\n\tUpdate order quantity successfully...\n");
					
					System.out.printf("New order status - %s\n",getOrderStatusName(burger[i].getOrderStatus()));
				}
			}
			L2:do{
				System.out.print("\n\nDo you want to update another order details (Y/N): ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					updateOrderDetails();
				}else if(retry.equals("n")){
					mainMenu();
				}else{
					System.out.print("Wrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void main(String args[]){
		mainMenu();
	}
	public static void clearConsole(){
		try{
			final String os=System.getProperty("os.name");
			if(os.contains("Windows")){
				new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
			}else{
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		}catch(final Exception e){
			e.printStackTrace();
		}
	}
}

