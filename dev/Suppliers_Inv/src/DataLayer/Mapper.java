package DataLayer;

import BussinessLayer.Inventory.Category;
import BussinessLayer.Inventory.Item;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DAO.*;
import DataLayer.DTO.BillOfQuantityDTO;
import DataLayer.DTO.ItemDTO;
import DataLayer.DTO.OrderDTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Mapper {
        /**
         * This class holds the identity maps of Employees and Shifts.
         * Any access to the data base goes through here.
         */
        private BillOfQuantityDAO billOfQuantityDAO;
        private CategoryDAO categoryDAO;
        private OrderDAO orderDAO;
        private ProductsInOrderDAO productsInOrderDAO;
        private ProductsOfSupplierDAO productsOfSupplierDAO;
        private SupplierDAO supplierDAO;
        private Map<Integer, BillOfQuantityDTO> billsOfQuantity;
        private Map<Integer, OrderDTO> orders;
        private ItemDAO itemDAO;

        private static class MapperHolder {
            private static Mapper instance = new Mapper();
        }

        private Mapper() {
            billOfQuantityDAO = new BillOfQuantityDAO();
            categoryDAO = new CategoryDAO();
            orderDAO = new OrderDAO();
            productsInOrderDAO = new ProductsInOrderDAO();
            productsOfSupplierDAO = new ProductsOfSupplierDAO();
            supplierDAO = new SupplierDAO();
            billsOfQuantity = new HashMap<>();
            orders = new HashMap<>();
            itemDAO = new ItemDAO();
        }

        public static Mapper getInstance(){
            return MapperHolder.instance;
        }

        public ResponseT<BillOfQuantityDTO> getBillOfQuantity(Integer supplierID) {
            if (billsOfQuantity.containsKey(supplierID))
                return new ResponseT<>(billsOfQuantity.get(supplierID));
            ResponseT<BillOfQuantityDTO> bill = billOfQuantityDAO.get(supplierID);
            if (!bill.ErrorOccured())
                billsOfQuantity.put(supplierID, bill.value);
            return bill;
        }

        public ResponseT<OrderDTO> getOrder(Integer orderID) {
            if (orders.containsKey(orderID))
                return new ResponseT<>(orders.get(orderID));
            ResponseT<OrderDTO> order = orderDAO.get(orderID);
            if (!order.ErrorOccured())
                orders.put(orderID, order.value);
            return order;
        }

        public ResponseT<List<Item>> loadItems() {
            ResponseT<List<ItemDTO>> itemsRes = itemDAO.read();
            List<Item> res= new LinkedList<>();
            if (!itemsRes.ErrorOccured()) {
                for(ItemDTO dbItem : itemsRes.value) {
                    res.add(new Item(dbItem));
                }
            } else {
                return new ResponseT<>("Could not load items");
            }
            return new ResponseT<>(res);
        }

        //for testing purpeses
        public void getItems() {
            ResponseT<List<ItemDTO>> result = itemDAO.read();
            System.out.println(result.value.get(0).getName());
        }

}
