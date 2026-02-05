package net.breezeware.order.entity;

import java.time.Instant;
import java.time.LocalDateTime;

public class Order {
int  id;
int user_id;
OrderStatus status;
Instant createdOn ;
Instant updatedOn ;


}
