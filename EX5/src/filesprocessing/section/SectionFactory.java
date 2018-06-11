package filesprocessing.section;

import filesprocessing.CoupleAndExceptions;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.OrderFactory;

import java.util.ArrayList;

/**
 * This class acts as a factory for the sections in this program.
 */
public class SectionFactory {

	/**
	 * Creates section according to given couple.
	 *
	 * @param couple: A couple of two strings and relevant exceptions, the section will be made of this.
	 * @return The section.
	 */
	public static Section createSection(CoupleAndExceptions<String> couple) {
		return new Section(FilterFactory.createFilter(couple.getA()), OrderFactory.createOrder(
				couple.getB()), couple.getExceptions());
	}

	/**
	 * Creates array of sections according to the given list of couples.
	 *
	 * @param couples: The list of couples we want to create the array of sections from.
	 * @return Array of sections.
	 */
	public static Section[] createSectionArray(ArrayList<CoupleAndExceptions<String>> couples) {
		Section[] sections = new Section[couples.size()];
		for (int i = 0; i < sections.length; i++) {
			sections[i] = createSection(couples.get(i));
		}
		return sections;
	}
}
