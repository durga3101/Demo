import com.trailblazers.freewheelers.UserJourneyBase;
import com.trailblazers.freewheelers.model.OrderStatus;
import org.junit.Ignore;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;
import static com.trailblazers.freewheelers.helpers.SyntaxSugar.from;
import static com.trailblazers.freewheelers.helpers.SyntaxSugar.to;

public class AdminFlow extends UserJourneyBase {
    @Test
    public void adminFlowTest() {
        String Hugo = "User Huser";
        String Arno = "Admin Admin";

        String Simplon_Frame = "Simplon Pavo 3 Ultra";
        String Spoke_Reflectors = "Spoke - Reflectors Arrow red";

        String New_Simplon_Name = "NEW - Simplon Pavo 3 Ultra";
        String New_Spoke_Name = "NEW - Spoke - Reflectors Arrow red";

        admin
                .there_is_no_account_for(Hugo)
                .there_is_no_account_for(Arno)
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_no_item(Simplon_Frame)
                .there_is_no_item(Spoke_Reflectors)
                .there_is_no_item(New_Simplon_Name)
                .there_is_no_item(New_Spoke_Name)
                .there_is_a_frame(CHROME_FRAME, 1l)
                .there_is_an_order(Hugo, CHROME_FRAME);
        user
                .visits_home_page()
                .logs_in_with(Arno, SOME_PASSWORD)
                .visits_admin_profile();
        screen
                .shows_admin_profile();
        user
                .visits_profile_for(Hugo);
        screen
                .shows_profile_for(Hugo);

        //Manage Orders
        user
                .visits_admin_profile();

        screen
                .there_should_be_an_order(CHROME_FRAME, "NEW");


        // Manage Items
        user
                .wants_to_manage_items()
                .creates_an_item(Simplon_Frame, "FRAME", NO_QUANTITY, REALLY_EXPENSIVE, SOME_DESCRIPTION);

        screen
                .shows_error("Please enter Item Quantity", "quantity_field");

        user
                .creates_an_item(Simplon_Frame, "FRAME", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION);
        screen
                .shows_in_manage_item_list(Simplon_Frame);

        user
                .creates_an_item(Spoke_Reflectors, "ACCESSORIES", A_LOT, REALLY_EXPENSIVE, SOME_DESCRIPTION);

        screen
                .shows_in_manage_item_list(Simplon_Frame)
                .shows_in_manage_item_list(Spoke_Reflectors);
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
                .changes_item_name(from(Simplon_Frame), to(New_Simplon_Name))
                .changes_item_name(from(Spoke_Reflectors), to(New_Spoke_Name));

        screen
                .shows_in_manage_item_list(New_Simplon_Name)
                .shows_in_manage_item_list(New_Spoke_Name);

        user
                .delete_item(New_Simplon_Name);

        screen
                .shows_in_manage_item_list(New_Spoke_Name)
                .shows_not_in_manage_item_list(New_Simplon_Name);


    }

}
