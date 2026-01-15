import{FoodItem} from "../../shared/models/FoodItem";
import {Restaurant} from "../../shared/models/restaurant";

export interface orderDTO{
    foodItemsList? : FoodItem[];
    userId? : number;
    restaurant? : Restaurant;
}