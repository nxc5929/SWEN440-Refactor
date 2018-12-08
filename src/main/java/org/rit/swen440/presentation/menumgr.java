package org.rit.swen440.presentation;

import org.rit.swen440.control.Controller;
import org.rit.swen440.dataLayer.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class menumgr {
    int currentLevel = 0;
    String currentCategoryName;
    String currentItemName;
    category currentCategory;
    item currentItem;
    private Controller controller;

    public menumgr() {
        controller = new Controller();

    }

    /**
     * Method looped from menutest until quit.
     *
     * @param level
     * @return
     */
    public boolean loadLevel(int level) {
        switch (currentLevel) {
            case -1:
                return true;
            case 0:
                Level0();
                break;
            case 1:
                Level1();
                break;
            default:
                System.out.println("Returning to main org.rit.swen440.presentation.menu");
                currentLevel = 0;
                Level0();
                break;
        }

        return false;
    }

    /**
     * Asks user what category of product they would like to access
     */
    public void Level0() {
        menu m = new menu();
        List<String> categories = controller.getCategories();
        m.loadMenu(categories);
        m.addMenuItem("'q' to Quit");
        System.out.println("The following org.rit.swen440.presentation.categories are available");
        m.printMenu();
        String result = "q";
        int iSel = -1;

        try {
            result = m.getSelection();
            iSel = Integer.parseInt(result);
        } catch (Exception e) {
            result = "q";
        }

        if (result.equals("q") || iSel < 0 ||
                iSel >= m.getMenuSize()-1) { //Must add -1 to include the added option 'q to Quit'
            currentLevel--;
        } else {
            currentLevel++;
            currentCategoryName = categories.get(iSel);
            System.out.println("\nYour Selection was:" + currentCategoryName);
        }
    }

    /**
     * Asks user what specific product they would like to purchase from their selected category.
     */
    public void Level1() {
        menu m = new menu();

        List<String> itemList = controller.getProducts(currentCategoryName);
        List<String> l = new ArrayList<>();
        System.out.println("");
        for (String itm : itemList)
            l.add(controller.getProductInformation(currentCategoryName, itm, Controller.PRODUCT_FIELD.NAME)
                    + "($" + controller.getProductInformation(currentCategoryName, itm, Controller.PRODUCT_FIELD.COST) + ")");

        m.loadMenu(l);
        m.addMenuItem("'q' to quit");
        System.out.println("The following items are available");
        m.printMenu();
        String result = m.getSelection();
        try {
            int iSel = Integer.parseInt(result);//Item  selected
            currentItemName = itemList.get(iSel);
            //currentItem = itemList.get(iSel);
            //Now read the file and print the org.rit.swen440.presentation.items in the catalog
            System.out.println("You want item from the catalog: " + currentItemName);
        } catch (Exception e) {
            result = "q";
        }
        if (result == "q")
            currentLevel--;
        else {
            //currentLevel++;//Or keep at same level?
            OrderQty(currentCategoryName, currentItemName);
        }
    }

    /**
     * Completes the purchase transaction
     *
     * @param category
     * @param item
     */
    public void OrderQty(String category, String item) {
        System.out.println("Please select a quantity");
        System.out.println(controller.getProductInformation(category, item, Controller.PRODUCT_FIELD.NAME) +
                " availability:" + controller.getProductInformation(category, item, Controller.PRODUCT_FIELD.INVENTORY));
        System.out.print(":");
        menu m = new menu();
        String result = m.getSelection();
        System.out.println("You ordered:" + result);
    }
}
