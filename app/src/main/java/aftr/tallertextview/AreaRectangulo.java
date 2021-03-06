package aftr.tallertextview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AreaRectangulo extends Activity {
    private EditText txtBase, txtAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_rectangulo);
        txtBase = findViewById(R.id.txtBase);
        txtAltura = findViewById(R.id.txtAltura);
    }

    public void resultado(View v){
        if (validar()) {
            double bas, alt, res;
            String op, dat, dec;
            bas = Double.parseDouble(txtBase.getText().toString());
            alt = Double.parseDouble(txtAltura.getText().toString());
            res = Metodos.areaRectangulo(bas, alt);
            //Respuesta con dos cifras decimales
            dec = String.valueOf(String.format("%.2f", res));

            //Crear objeto y guardarlo en datos
            op = getResources().getString(R.string.areaRectangulo);
            dat = getResources().getString(R.string.base) + String.valueOf(bas) + "\n"
                    + getResources().getString(R.string.altura) + String.valueOf(alt);
            Operacion o = new Operacion(op, dat, dec);
            o.guardar();

            //Mostrar resultado en dialogo
            String mensaje = getResources().getString(R.string.area) + " " + dec;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.resultado);
            builder.setMessage(mensaje);
            builder.setNeutralButton("Ok", null);
            Dialog dialog = builder.create();
            dialog.show();
        }
    }

    public boolean validar(){
        if (txtBase.getText().toString().isEmpty()){
            txtBase.setError(getResources().getString(R.string.error_1));
            txtBase.requestFocus();
            return false;
        }
        if (txtBase.getText().toString().equals(".")){
            txtBase.setError(getResources().getString(R.string.error_2));
            txtBase.requestFocus();
            return false;
        }
        double valor = Double.parseDouble(txtBase.getText().toString());
        if (valor<=0){
            txtBase.setError(getResources().getString(R.string.error_2));
            txtBase.requestFocus();
            return false;
        }
        if (txtAltura.getText().toString().isEmpty()){
            txtAltura.setError(getResources().getString(R.string.error_1));
            txtAltura.requestFocus();
            return false;
        }
        if (txtAltura.getText().toString().equals(".")){
            txtAltura.setError(getResources().getString(R.string.error_2));
            txtAltura.requestFocus();
            return false;
        }
        double valor2 = Double.parseDouble(txtAltura.getText().toString());
        if (valor2<=0){
            txtAltura.setError(getResources().getString(R.string.error_2));
            txtAltura.requestFocus();
            return false;
        }
        return true;
    }

    public void borrar(View v){
        txtBase.setText("");
        txtAltura.setText("");
        txtBase.requestFocus();
    }
}
