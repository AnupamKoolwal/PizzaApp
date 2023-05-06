import { Pizza } from "./pizza"

export type Order = {
    orderId:number,
    orderDate: Date,
    orderPizzaList: Pizza[],
    orderTotalPrice:number
}