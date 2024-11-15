// src/LoginScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';
import axios from 'axios';
import { useNavigation } from '@react-navigation/native';

const LoginScreen = () => {
    const [username, setUsername] = useState('user');
    const [password, setPassword] = useState('test123');
    const [error, setError] = useState('');
    const navigation = useNavigation();

    const handleLogin = async () => {
        try {
             const response = await axios.post('http://localhost:8080/api/auth', {
                            username,
                            password,
                        });
            //const { token } = response.data;
            // Save the token securely
            // You can use AsyncStorage for secure storage
//            await AsyncStorage.setItem('token', response.data.token);
            navigation.navigate('Products');
        } catch (error) {
            setError('Login failed. Please check your credentials and try again');
        }
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Sign In</Text>
            <TextInput
                style={styles.input}
                placeholder="Username"
                value={username}
                onChangeText={setUsername}
                autoCapitalize="none"
            />
            <TextInput
                style={styles.input}
                placeholder="Password"
                value={password}
                onChangeText={setPassword}
                secureTextEntry
            />
            {error ? <Text style={styles.error}>{error}</Text> : null}
            <Button title="Sign In" onPress={handleLogin} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        padding: 16,
    },
    title: {
        fontSize: 24,
        marginBottom: 16,
        textAlign: 'center',
    },
    input: {
        height: 40,
        borderColor: 'gray',
        borderWidth: 1,
        marginBottom: 12,
        paddingLeft: 8,
    },
    error: {
        color: 'red',
        marginBottom: 12,
        textAlign: 'center',
    },
});

export default LoginScreen;