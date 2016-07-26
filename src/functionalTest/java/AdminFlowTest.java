import com.trailblazers.freewheelers.UserJourneyBase;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class AdminFlowTest extends UserJourneyBase {

    public static final String EMPTY_IMAGE_URL = "https://s6.postimg.org/qopocyf41/product_image_coming_soon.png";
    public static final String ITEM_IMAGE_URL = "https://s6.postimg.org/cahp7h3wh/Scattante_XRLComp_Road_Bike_small.jpg";

    @Test
    public void adminFlowTest() {
        String Hugo = "User Huser";
        String Arno = "Admin Admin";

        String ArnoEmail = emailFor(Arno);
        String HugoEmail = emailFor(Hugo);

        String New_Simplon_Name = "NEW - Simplon Pavo 3 Ultra";
        String New_Spoke_Name = "NEW - Spoke - Reflectors Arrow red";
        String New_Item_Without_Spaces = "Pokemon";

        admin
                .there_is_no_account_for(Hugo)
                .there_is_no_account_for(Arno)
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_no_item(SIMPLON_FRAME)
                .there_is_no_item(SPOKE_REFLECTORS)
                .there_is_no_item(New_Simplon_Name)
                .there_is_no_item(New_Spoke_Name)
                .there_is_no_item(New_Item_Without_Spaces)
                .there_is_a_frame(CHROME_FRAME, 1l);

        Long ORDER_ID = admin.there_is_an_order(Hugo, CHROME_FRAME);

        user
                .visits_home_page()
                .logs_in_with(ArnoEmail, SOME_PASSWORD);
        screen
                .should_contain_admin_profile_link_in_header();
        user
                .visits_admin_profile();
        screen
                .shows_admin_profile();
        user
                .visits_profile_for(HugoEmail);
        screen
                .shows_profile_for(Hugo);

        //Manage Orders
        user
                .visits_admin_profile();

        screen
                .there_should_be_an_order(CHROME_FRAME, "NEW", ORDER_ID);
        user
                .changes_order_status(CHROME_FRAME, "IN_PROGRESS");
        screen
                .there_should_be_an_order(CHROME_FRAME, "IN_PROGRESS", ORDER_ID);
        user
                .clicks_on_user_name_in_order_table(Hugo);
        screen
                .shows_profile_for(Hugo);

        // Manage Items
        user
                .wants_to_manage_items()
                .creates_an_item(SIMPLON_FRAME, "FRAME", NO_QUANTITY, REALLY_EXPENSIVE, SOME_DESCRIPTION, ITEM_IMAGE_URL);

        screen
                .shows_error("Please enter Item Quantity", "quantity_field");

        user
                .creates_an_item(SIMPLON_FRAME, "FRAME", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION, ITEM_IMAGE_URL);
        screen
                .shows_in_manage_item_list(SIMPLON_FRAME)
                .show_image_url_for_the_item(SIMPLON_FRAME, ITEM_IMAGE_URL);

        user
                .creates_an_item(New_Item_Without_Spaces, "FRAME", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION,  EMPTY_IMAGE_URL);
        screen
                .shows_in_manage_item_list(New_Item_Without_Spaces)
                .show_image_url_for_the_item(New_Item_Without_Spaces,  EMPTY_IMAGE_URL);

        user
                .creates_an_item(SPOKE_REFLECTORS, "ACCESSORIES", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION, ITEM_IMAGE_URL);

        screen
                .shows_in_manage_item_list(SIMPLON_FRAME)
                .shows_in_manage_item_list(SPOKE_REFLECTORS);
        user
                .changes_item_quantity(from(A_LOT), to("-10"))
                .changes_item_price(from(REALLY_EXPENSIVE), to("-10.00"));

        screen

                .pageElementContainsMessage("Please enter valid Item Quantity")
                .pageElementContainsMessage("Please enter valid Item Price");

        user
                .changes_item_quantity(from(A_LOT), to(A_LOT))
                .changes_item_price(from(REALLY_EXPENSIVE), to(REALLY_EXPENSIVE));


        user
                .changes_item_name(from(SIMPLON_FRAME), to(New_Simplon_Name))
                .changes_item_name(from(SPOKE_REFLECTORS), to(New_Spoke_Name));

        screen
                .shows_in_manage_item_list(New_Simplon_Name)
                .shows_in_manage_item_list(New_Spoke_Name);
        user
                .changes_item_image_url(New_Item_Without_Spaces, ITEM_IMAGE_URL);

        screen
                .show_image_url_for_the_item(New_Item_Without_Spaces, ITEM_IMAGE_URL);

        user
                .delete_item(New_Simplon_Name);

        screen
                .shows_in_manage_item_list(New_Spoke_Name)
                .shows_not_in_manage_item_list(New_Simplon_Name);


    }

}
