// src/ProductsScreen.js
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const ProductsScreen = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.title}>Products</Text>
            {/* Add your product list or content here */}
            <Text>Product 1</Text>
            <Text>Product 2</Text>
            <Text>Product 3</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 16,
    },
    title: {
        fontSize: 24,
        marginBottom: 16,
    },
});

export default ProductsScreen;