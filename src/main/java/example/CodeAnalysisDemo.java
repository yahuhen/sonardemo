package example;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Random;

public class CodeAnalysisDemo {

    // Bugs
    public int divideByZero(int numerator, int denominator) {
        return numerator / denominator;
    }

    public String nullPointer(String input) {
        return input.toUpperCase();
    }

    public void infiniteLoop() {
        while (true) {
            System.out.println("This is an infinite loop!");
        }
    }

    // Vulnerabilities
    public void insecureRandom() {
        Random random = new Random();
        int insecureRandomNumber = random.nextInt();
        System.out.println("Insecure random number: " + insecureRandomNumber);
    }

    public String weakHashing(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashedBytes = md.digest(password.getBytes());
        return new String(hashedBytes);  // This is weak and should use a better hashing algorithm.
    }

    public void processRuntime(String command) throws IOException {
        Runtime.getRuntime().exec(command);  // This is insecure and vulnerable to command injection.
    }

    // In a real application, this code would use weak SSL/TLS configurations.
    // For educational purposes only.
    public void weakSSL() throws NoSuchAlgorithmException, KeyManagementException {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Create an all-trusting host name verifier
        HostnameVerifier allHostsValid = (hostname, session) -> true;

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        try (Socket socket = new Socket("weak-tls-server.com", 443);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            // Perform weak SSL/TLS communication for educational purposes only.
            // In a real-world scenario, always use secure configurations.
            String message = "Hello, Weak TLS Server!";
            output.writeUTF(message);
            System.out.println("Server Response: " + input.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Security Hotspots
    public void hardCodedCredentials() {
        String username = "admin";
        String password = "admin123";
        System.out.println("Logging sensitive information: " + username);
        System.out.println("Logging sensitive information: " + password);
        // In a real application, credentials should not be hard-coded like this.
        // In a real application, logging sensitive information is a security hotspot.
        // For educational purposes only.
    }

    // Code Smells
    public void duplicatedCode() {
        System.out.println("This is duplicated code.");
        System.out.println("This is duplicated code.");  // Duplicated code smell.
    }

    private static void longMethod(String orderId, String customerName, String productName,
                                     int quantity, double price, String paymentMethod) {
        System.out.println("This is a long method with many lines.");

        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Invalid customer name");
        }

        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Invalid product name");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        if (price <= 0.0) {
            throw new IllegalArgumentException("Invalid price");
        }

        if (paymentMethod == null || paymentMethod.isEmpty()) {
            throw new IllegalArgumentException("Invalid payment method");
        }

        System.out.println("Processing Order:");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Product: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: $" + price);
        System.out.println("Payment Method: " + paymentMethod);

        double totalCost = quantity * price;

        // Apply discount
        if (totalCost > 500.0) {
            totalCost *= 0.9; // 10% discount for orders over $500
        }

        // Process payment
        boolean paymentSuccess;

        if ("Credit Card".equalsIgnoreCase(paymentMethod)) {
            paymentSuccess =  new Random().nextBoolean();
        } else if ("PayPal".equalsIgnoreCase(paymentMethod)) {
            paymentSuccess =  new Random().nextBoolean();
        } else {
            throw new IllegalArgumentException("Unsupported payment method");
        }

        // Update inventory
        System.out.println("Updating inventory for " + productName + " - Quantity: " + quantity);

        // Send order confirmation email
        System.out.println("Sending order confirmation email to " + customerName);
        System.out.println("Order Details:\nProduct: " + productName + "\nQuantity: " + quantity +
                "\nTotal Cost: $" + totalCost + "\nPayment Success: " + paymentSuccess);

        System.out.println("End of long method.");
    }

    public void tooManyParameters(int param1, int param2, int param3, int param4, int param5,
                                  int param6, int param7, int param8, int param9, int param10) {
        // Too many parameters is a code smell.
    }

    public void magicNumber() {
        int result = 42 * 3;  // Magic number smell.
        System.out.println("Result: " + result);
    }

    public void unusedVariable() {
        int unusedVar = 5;  // Unused variable smell.
        System.out.println("This variable is not used: " + unusedVar);
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        CodeAnalysisDemo demo = new CodeAnalysisDemo();
        demo.divideByZero(10, 0);
        demo.nullPointer(null);
        demo.infiniteLoop();
        demo.insecureRandom();
        demo.weakHashing("password123");
        demo.processRuntime("ls -la");
        demo.weakSSL();
        demo.hardCodedCredentials();
        demo.duplicatedCode();
        demo.longMethod("123456", "John Doe",
                "ProductABC", 5, 100.0,
                "Credit Card");
        demo.tooManyParameters(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        demo.magicNumber();
        demo.unusedVariable();
    }
}
