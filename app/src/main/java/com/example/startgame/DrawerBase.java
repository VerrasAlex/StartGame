package com.example.startgame;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.fonts.Font;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


/*
Klash pou ftiaxnei to navigation drawer pou uparxei sth klash Home kai MyGames.
H klash xrhsimopoiei mia vivlio8hkh pou parexei etoimes me8odous gia thn dhmiourgia kai
thn prosarmogh tou Navigation drawer.
H monadikh statikh me8odos pou yparxei kaleitai mesa apo tis alles klaseis kai ftiaxnei
to navigation drawer
Den xreiazetai kapoio xml arxeio pou na antistoixei se ayth th klash.
 */
public class DrawerBase extends AppCompatActivity
{
    public static void getDrawer(final AppCompatActivity activity, Toolbar toolbar, final String mail) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        IProfile profile = new ProfileDrawerItem()
                .withEmail(mail).withIcon(R.drawable.baseline_account_circle_black_48dp).withIdentifier(100);

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.ab_background)
                .withSelectionListEnabled(false)
                .withTranslucentStatusBar(true)
                .addProfiles(profile)
                .build();

        //Ta antikeimena pou emfanizontai prwta
        PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(1)
                .withName("Home Feed").withIcon(R.drawable.baseline_home_black_18dp);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName("My Games").withIcon(R.drawable.baseline_emoji_events_black_24dp);


        //ta antikeimena pou emfanizontai deytera
        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName("Settings").withIcon(R.drawable.ic_menu_manage);
        SecondaryDrawerItem drawerItemAddField = new SecondaryDrawerItem().withIdentifier(7)
                .withName("Add a new Field").withIcon(R.drawable.baseline_add_location_alt_black_18dp);

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        drawerItemManagePlayers,
                        drawerItemManagePlayersTournaments,
                        new DividerDrawerItem(),
                        drawerItemSettings,
                        drawerItemAddField
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1) {
                            Intent intent = new Intent(activity, Home.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 2){
                            Intent intent = new Intent(activity, MyGames.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 3){
                            Intent intent = new Intent(activity, SettingsActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 4){
                            Intent intent = new Intent(activity, Home.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 5){
                            Intent intent = new Intent(activity, Home.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 6){
                            Intent intent = new Intent(activity, Home.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 7){
                            DialogFragment dialogFragment = new AddFieldDialogFragment();
                            dialogFragment.show(activity.getSupportFragmentManager(), "dialog");
                        }

                        return true;
                    }
                })
                .build();
    }
}


