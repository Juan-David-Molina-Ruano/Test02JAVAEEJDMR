package com.test.Test02JAVAEEJDMR.Controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.Test02JAVAEEJDMR.Models.DetalleOrdenJDMR;
import com.test.Test02JAVAEEJDMR.Models.OrdenJDMR;
import com.test.Test02JAVAEEJDMR.Models.ProductoJDMR;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IDetalleOrdenJDMRService;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IOrdenJDMRService;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IProductoJDMRService;

@Controller
@RequestMapping("/detallesorden")
public class DetalleOrdenController {

    @Autowired
    private IDetalleOrdenJDMRService detalleOrdenService;

    @Autowired
    private IProductoJDMRService productoJDMRService;

    @Autowired
    private IOrdenJDMRService ordenJDMRService;


     @GetMapping("/index")
    public String index(Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        Page<DetalleOrdenJDMR> detalles = detalleOrdenService.buscarTodosPaginados(pageable);
        model.addAttribute("detalles", detalles);

        if (detalles.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, detalles.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "detallesorden/index";
    }

    @GetMapping("/create")
    public String create(DetalleOrdenJDMR detalleOrdenJDMR, Model model){
        model.addAttribute("ordenes", ordenJDMRService.obtenerTodos());
        model.addAttribute("productos", productoJDMRService.obtenerTodos());

        return "detallesorden/create";
    }

    @PostMapping("/save")
public String save(
    @RequestParam Long producto_id,
    @RequestParam Long orden_id,
    @RequestParam Integer cantidadJDMR,
    @RequestParam BigDecimal precioJDMR,
    RedirectAttributes attributes
) {
    // Verifica si el producto y la orden existen (esto depende de tu implementación de servicios)
    ProductoJDMR producto = productoJDMRService.buscarPorId(producto_id).orElse(null);
    OrdenJDMR orden = ordenJDMRService.buscarPorId(orden_id).orElse(null);

    if (producto != null && orden != null) {
        // Crea un nuevo objeto DetalleOrdenJDMR
        DetalleOrdenJDMR detalleOrdenJDMR = new DetalleOrdenJDMR();
        detalleOrdenJDMR.setProductoJDMR(producto);
        detalleOrdenJDMR.setOrdenJDMR(orden);
        detalleOrdenJDMR.setCantidadJDMR(cantidadJDMR);
        detalleOrdenJDMR.setPrecioJDMR(precioJDMR);

        // Guarda el objeto en la base de datos (esto depende de tu implementación de servicios)
        detalleOrdenService.crearOEditar(detalleOrdenJDMR);

        // Añade un mensaje de éxito para mostrar en la vista
        attributes.addFlashAttribute("msg", "Detalle de orden guardado correctamente");
    } else {
        // Añade un mensaje de error en caso de que producto o orden no se encuentren
        attributes.addFlashAttribute("error", "Producto o orden no encontrados");
    }

    // Redirige a la vista de la lista de detalles de orden
    return "redirect:/detallesorden/index";
}

}
