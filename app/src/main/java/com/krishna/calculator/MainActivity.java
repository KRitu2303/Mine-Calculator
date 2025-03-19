package com.krishna.calculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.krishna.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.TxtResult.setText("0");
        binding.TxtExpression.setText("");

        binding.clearBtn.setOnClickListener(v -> {
            binding.TxtExpression.setText("");
            binding.TxtResult.setText("0");
        });

        binding.oneBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("1");
        });

        binding.twoBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("2");
        });

        binding.threeBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("3");
        });

        binding.fourBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("4");
        });

        binding.fiveBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("5");
        });

        binding.sixBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("6");
        });

        binding.sevenBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("7");
        });

        binding.eightBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("8");
        });

        binding.nineBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("9");
        });

        binding.zeroBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("0");
        });

        binding.doubleZero.setOnClickListener(v -> {
            binding.TxtExpression.append("00");
        });

        binding.percentBtn.setOnClickListener(V -> {
            binding.TxtExpression.append("%");
        });

        binding.backBtn.setOnClickListener(v -> {
            int length = binding.TxtExpression.getText().length();
            if(length > 0){
                String expression = binding.TxtExpression.getText().toString();
                binding.TxtExpression.setText(expression.substring(0, length-1));
            }
        });

        binding.divideBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("/");
        });

        binding.multiplyBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("*");
        });

        binding.minusBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("-");
        });

        binding.plusBtn.setOnClickListener(v -> {
            binding.TxtExpression.append("+");
        });

        binding.decimalBtn.setOnClickListener(v -> {
            binding.TxtExpression.append(".");
        });


        binding.equalBtn.setOnClickListener(v -> {
            String expression = binding.TxtExpression.getText().toString();

            try{
                float result = evaluateExpression(expression);
                binding.TxtResult.setText(String.valueOf(result));
            } catch (Exception e){
                binding.TxtResult.setText("Error");
            }
        });


    }

    private float evaluateExpression(String expression) {
        float result = 0;
        float previousNumber = 0; // Initialize previousNumber
        String[] parts = expression.split("(?<=[-+*/%])|(?=[-+*/%])");

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            try {
                float number = Float.parseFloat(part);
                previousNumber = number; // Update previousNumber
                if (i == 0) {
                    result = number;
                }
            } catch (NumberFormatException e) {
                if (part.equals("+")) {
                    result = add(result, Float.parseFloat(parts[++i]));
                } else if (part.equals("-")) {
                    result = minus(result, Float.parseFloat(parts[++i]));
                } else if (part.equals("*")) {
                    result = multiply(result, Float.parseFloat(parts[++i]));
                } else if (part.equals("/")) {
                    result = divide(result, Float.parseFloat(parts[++i]));
                } else if (part.equals("%")) {
                    result = percent(previousNumber); // Apply percent to previousNumber
                    previousNumber = result; // Update previousNumber with the percent result
                }
            }
        }

        return result;
    }


    private float add(float a, float b){
        return a+b;
    }

    private float minus(float a, float b){
        return a-b;
    }

    private float multiply(float a, float b){
        return a*b;
    }

    private float divide(float a, float b){
        return a/b;
    }

    private float percent(float a){
        return a/100;
    }
}