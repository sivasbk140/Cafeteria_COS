PRAGMA foreign_keys = ON;


CREATE TABLE IF NOT EXISTS Users (
                                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                                     name TEXT NOT NULL,
                                     password TEXT NOT NULL,
                                     role TEXT NOT NULL CHECK (
                                     role IN ('ADMIN', 'STAFF', 'CUSTOMER','DELIVERY_STAFF')
    ),
    email TEXT NOT NULL UNIQUE,
    created_on  TIMESTAMP,
    updated_on  TIMESTAMP
    );


CREATE TABLE IF NOT EXISTS Food_Item (
                                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                                         name TEXT NOT NULL,
                                         price REAL NOT NULL,
                                         created_on  TIMESTAMP,
                                         updated_on  TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Food_Menu (
                                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                                         category TEXT NOT NULL,
                                         created_on  TIMESTAMP,
                                         updated_on  TIMESTAMP
);


CREATE TABLE IF NOT EXISTS Food_Menu_Items_Map (
                                                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                                                   menu_id INTEGER NOT NULL,
                                                   food_item_id INTEGER NOT NULL,
                                                   is_available INTEGER DEFAULT 1,
                                                   display_order INTEGER DEFAULT 0,
                                                   created_on  TIMESTAMP,
                                                   updated_on  TIMESTAMP,
                                                   FOREIGN KEY (menu_id) REFERENCES Food_Menu(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (food_item_id) REFERENCES Food_Item(id)
    ON DELETE CASCADE ON UPDATE CASCADE
    );


CREATE TABLE IF NOT EXISTS Availability_Map (
                                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                                menu_id INTEGER NOT NULL,
                                                menu_day TEXT NOT NULL CHECK (
                                                menu_day IN (
                                                'MONDAY','TUESDAY','WEDNESDAY',
                                                'THURSDAY','FRIDAY','SATURDAY','SUNDAY')),
    created_on  TIMESTAMP,
    updated_on  TIMESTAMP,
    FOREIGN KEY (menu_id) REFERENCES Food_Menu(id)
    ON DELETE CASCADE ON UPDATE CASCADE
    );


CREATE TABLE IF NOT EXISTS Order_Table (
                                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                                           user_id INTEGER NOT NULL,
                                           status TEXT NOT NULL CHECK (
                                           status IN (
                                           'PLACED_ORDER',
                                           'ORDER_DELIVERED',
                                           'ORDER_CANCELLED',
                                           'PENDING_DELIVERY',
                                           'WAITING_FOR_DELIVERY')),
    created_on  TIMESTAMP,
    updated_on  TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id)
    ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS Order_Items (
                                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                                           order_id INTEGER NOT NULL,
                                           food_menu_item_id INTEGER NOT NULL,
                                           price REAL NOT NULL,
                                           quantity INTEGER NOT NULL,
                                           created_on  TIMESTAMP,
                                           updated_on  TIMESTAMP,
                                           FOREIGN KEY (order_id) REFERENCES Order_Table(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (food_menu_item_id) REFERENCES Food_Menu_Items_Map(id)
    ON DELETE CASCADE ON UPDATE CASCADE
    );


CREATE TABLE IF NOT EXISTS Delivery_Details (
                                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                                email TEXT NOT NULL,
                                                phone_number TEXT NOT NULL,
                                                location TEXT NOT NULL,
                                                user_id INTEGER NOT NULL,
                                                created_on  TIMESTAMP,
                                                updated_on  TIMESTAMP,
                                                FOREIGN KEY (user_id) REFERENCES Users(id)
    ON DELETE CASCADE ON UPDATE CASCADE
    );


CREATE TABLE IF NOT EXISTS Order_Delivery_Map (
                                                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                                                  order_id INTEGER NOT NULL,
                                                  delivery_id INTEGER NOT NULL,
                                                  created_on TIMESTAMP,
                                                  updated_on TIMESTAMP,
                                                  FOREIGN KEY (order_id) REFERENCES Order_Table(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (delivery_id) REFERENCES Delivery_Details(id)
    ON DELETE CASCADE ON UPDATE CASCADE
    );



