package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.DigitalVideoGame;
import model.Sale;
import model.VideoGame;
import service.VideoGameService;

public class MainApplication extends Application {

    private VideoGameService service = new VideoGameService();
    private TextArea area = new TextArea();

    @Override
    public void start(Stage stage) {

        Button btnAgregar = new Button("1. Agregar Videojuego");
        Button btnListar = new Button("2. Listar Todos");
        Button btnBuscarTitulo = new Button("3. Buscar por Título");
        Button btnBuscarPlataforma = new Button("4. Buscar por Plataforma");
        Button btnActualizar = new Button("5. Actualizar");
        Button btnVenta = new Button("6. Realizar Venta");
        Button btnVentas = new Button("7. Mostrar Ventas");
        Button btnEliminar = new Button("8. Eliminar");
        Button btnSalir = new Button("9. Salir");

        btnAgregar.setOnAction(e -> agregarJuego());
        btnListar.setOnAction(e -> listarJuegos());
        btnBuscarTitulo.setOnAction(e -> buscarTitulo());
        btnBuscarPlataforma.setOnAction(e -> buscarPlataforma());
        btnActualizar.setOnAction(e -> actualizarJuego());
        btnVenta.setOnAction(e -> realizarVenta());
        btnVentas.setOnAction(e -> mostrarVentas());
        btnEliminar.setOnAction(e -> eliminarJuego());
        btnSalir.setOnAction(e -> Platform.exit());

        area.setEditable(false);

        VBox root = new VBox(10);

        root.getChildren().addAll(
                btnAgregar,
                btnListar,
                btnBuscarTitulo,
                btnBuscarPlataforma,
                btnActualizar,
                btnVenta,
                btnVentas,
                btnEliminar,
                btnSalir,
                area
        );

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("SISTEMA DE GESTIÓN - GAMEZONE");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarJuego() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Título del videojuego");

        dialog.showAndWait().ifPresent(titulo -> {

            try {

                VideoGame game =
                        new DigitalVideoGame(
                                titulo,
                                "PC",
                                100000,
                                10,
                                80
                        );

                service.addVideoGame(game);

                area.appendText("Agregado: "
                        + titulo + "\n");

            } catch (Exception ex) {

                mostrarError(ex.getMessage());
            }
        });
    }

    private void listarJuegos() {

        area.clear();

        for(VideoGame game :
                service.getAllGames()) {

            area.appendText(
                    game.toString() + "\n"
            );
        }
    }

    private void buscarTitulo() {

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setHeaderText("Título");

        dialog.showAndWait().ifPresent(titulo -> {

            VideoGame game =
                    service.searchByTitle(titulo);

            if(game != null) {
                area.setText(game.toString());
            } else {
                area.setText("No encontrado");
            }
        });
    }

    private void buscarPlataforma() {

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setHeaderText("Plataforma");

        dialog.showAndWait().ifPresent(plataforma -> {

            area.clear();

            for(VideoGame game :
                    service.searchByPlatform(plataforma)) {

                area.appendText(
                        game.toString() + "\n"
                );
            }
        });
    }

    private void actualizarJuego() {

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setHeaderText(
                "Título a actualizar"
        );

        dialog.showAndWait().ifPresent(titulo -> {

            try {

                VideoGame nuevo =
                        new DigitalVideoGame(
                                titulo + "_nuevo",
                                "PC",
                                150000,
                                20,
                                90
                        );

                service.updateGame(
                        titulo,
                        nuevo
                );

                area.appendText(
                        "Actualizado\n"
                );

            } catch (Exception ex) {

                mostrarError(ex.getMessage());
            }
        });
    }

    private void realizarVenta() {

        TextInputDialog tituloDialog =
                new TextInputDialog();

        tituloDialog.setHeaderText(
                "Título del juego"
        );

        tituloDialog.showAndWait()
                .ifPresent(titulo -> {

                    try {

                        double total =
                                service.sellGame(
                                        titulo,
                                        1
                                );

                        area.appendText(
                                "Venta realizada. Total: "
                                        + total + "\n"
                        );

                    } catch (Exception ex) {

                        mostrarError(
                                ex.getMessage()
                        );
                    }
                });
    }

    private void mostrarVentas() {

        area.clear();

        for(Sale sale :
                service.getSales()) {

            area.appendText(
                    sale.toString() + "\n"
            );
        }
    }

    private void eliminarJuego() {

        TextInputDialog dialog =
                new TextInputDialog();

        dialog.setHeaderText("Título");

        dialog.showAndWait().ifPresent(titulo -> {

            service.deleteGame(titulo);

            area.appendText(
                    "Eliminado: "
                            + titulo + "\n"
            );
        });
    }

    private void mostrarError(String mensaje) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
