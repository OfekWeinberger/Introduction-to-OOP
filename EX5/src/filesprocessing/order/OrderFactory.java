package filesprocessing.order;

/**
 * This class implements the .
 */
public class OrderFactory {

	public static final String ABS = "abs", SIZE = "size", TYPE = "type", REVERSE = "REVERSE";

	public static Order createOrder(String orderString) {
		Order order = null;
		String orderName;
		int index = orderString.indexOf("#");
		if (index > 0) {
			orderName = orderString.substring(0, index);
		} else {
			orderName = orderString;
		}
		switch (orderName) {
			case ABS:
				order = AbsOrder.absOrder();
				break;
			case SIZE:
				order = SizeOrder.sizeOrder();
				break;
			case TYPE:
				order = TypeOrder.typeOrder();
				break;
			default:
				order = AbsOrder.absOrder();
				break;
		}
		if (index > 0) {
			order = new ReverseOrder(order);
		}
		return order;
	}

}