package com.example.recipeapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.recipeapp.Model.Constants;

import io.paperdb.Paper;

/**
 * Implementation of App Widget functionality.  I used this website as a resource to create
 * a widget.  https://www.youtube.com/watch?v=I6lXx-VgaYs
 */
public class IngredientsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Paper.init(context);

        String ingredients = Paper.book().read(Constants.SEND_INGREDIENTS_LIST_TO_WIDGET);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
        views.setTextViewText(R.id.appwidget_text, ingredients);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

