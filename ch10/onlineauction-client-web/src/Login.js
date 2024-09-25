import React, {useEffect, useState} from 'react';
import { Button, TextField, Container, Typography, Box, CssBaseline } from '@mui/material';
import { Google as GoogleIcon } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    // Function to handle login with Google
    const handleLogin = () => {
        window.location.href = "http://localhost:8072/authentication/login/oauth2/code/google";
    };

    // Function to handle token validation
    const validateToken = async (token) => {
        try {
            const response = await axios.get(`http://localhost:8072/authentication/v1/api/auth/validate?token=${token}`);
            return response.status === 200; // Return true if token is valid
        } catch (error) {
            return false; // Return false if token validation fails
        }
    };

    useEffect(() => {
        // Check if a token exists in localStorage and validate it
        const token = localStorage.getItem('token');
        if (token) {
            validateToken(token).then((isValid) => {
                if (isValid) {
                    // Redirect to the products page if the token is valid
                    navigate('/products');
                }
            });
        } else {
            // Check if a token has been returned in the URL from the OAuth2 flow
            const queryParams = new URLSearchParams(window.location.search);
            const token = queryParams.get('token'); // Assuming the token is returned as a query parameter
            if (token) {
                localStorage.setItem('token', token); // Store token in localStorage
                navigate('/products'); // Redirect to products page
            }
        }
    }, [navigate]);

    // Function to handle form submission for traditional login
    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8072/authentication/v1/api/auth', {
                username,
                password,
            });
            localStorage.setItem('token', response.data.token); // Store token in localStorage
            navigate('/products'); // Redirect to products page
        } catch (error) {
            setError('Login failed. Please check your credentials and try again.');
        }
    };

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    Sign In
                </Typography>
                <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        label="Username"
                        name="username"
                        autoComplete="username"
                        autoFocus
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    {error && (
                        <Typography color="error" variant="body2">
                            {error}
                        </Typography>
                    )}
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Sign In
                    </Button>
                    <Button
                        onClick={handleLogin}
                        fullWidth
                        variant="contained"
                        sx={{
                            mt: 3,
                            mb: 2,
                            backgroundColor: '#db4437', // Google's red color
                            '&:hover': { backgroundColor: '#c23321' } // Darker red on hover
                        }}
                        startIcon={<GoogleIcon />}
                    >
                        Login with Google
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default Login;
// // src/Login.js
// import React, {useEffect, useState} from 'react';
// import { Button, TextField, Container, Typography, Box, CssBaseline } from '@mui/material';
// import { Google as GoogleIcon } from '@mui/icons-material';
// import { useNavigate } from 'react-router-dom';
// import axios from 'axios';
//
// const Login = () => {
//     const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');
//     const [error, setError] = useState('');
//     const navigate = useNavigate();
//
//     const handleLogin = () => {
//         window.location.href = "http://localhost:8072/authentication/login/oauth2/code/google";
//     };
//
//     // Function to validate the token by sending a request to the backend
//     const validateToken = async (token) => {
//         try {
//             const response = await axios.get(`http://localhost:8072/authentication/v1/api/auth/validate?token=${token}`);
//             // If the token is valid, return true
//             return response.status === 200;
//         } catch (error) {
//             // If the token is invalid or the request fails, return false
//             return false;
//         }
//     };
//
//     useEffect(() => {
//         // Check if token exists in localStorage
//         const token = localStorage.getItem('token');
//         if (token) {
//             // Validate the token (optional, if you want to check it against the backend)
//             validateToken(token).then((isValid) => {
//                 if (isValid) {
//                     // If valid, navigate to the products page
//                     navigate('/products');
//                 }
//             });
//         }
//     }, [navigate]);
//
//     const handleSubmit = async (event) => {
//         event.preventDefault();
//         try {
//             const response = await axios.post('http://localhost:8072/authentication/v1/api/auth', {
//                 username,
//                 password,
//             });
//             // const { token } = response.data.token;
//             localStorage.setItem('token', response.data.token);
//             navigate('/products');
//         } catch (error) {
//             setError('Login failed. Please check your credentials and try again.');
//         }
//     };
//
//     return (
//         <Container component="main" maxWidth="xs">
//             <CssBaseline />
//             <Box
//                 sx={{
//                     marginTop: 8,
//                     display: 'flex',
//                     flexDirection: 'column',
//                     alignItems: 'center',
//                 }}
//             >
//                 <Typography component="h1" variant="h5">
//                     Sign In
//                 </Typography>
//                 <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
//                     <TextField
//                         variant="outlined"
//                         margin="normal"
//                         required
//                         fullWidth
//                         id="username"
//                         label="Username"
//                         name="username"
//                         autoComplete="username"
//                         autoFocus
//                         value={username}
//                         onChange={(e) => setUsername(e.target.value)}
//                     />
//                     <TextField
//                         variant="outlined"
//                         margin="normal"
//                         required
//                         fullWidth
//                         name="password"
//                         label="Password"
//                         type="password"
//                         id="password"
//                         autoComplete="current-password"
//                         value={password}
//                         onChange={(e) => setPassword(e.target.value)}
//                     />
//                     {error && (
//                         <Typography color="error" variant="body2">
//                             {error}
//                         </Typography>
//                     )}
//                     <Button
//                         type="submit"
//                         fullWidth
//                         variant="contained"
//                         sx={{ mt: 3, mb: 2 }}
//                     >
//                         Sign In
//                     </Button>
//                     <Button
//                         onClick={handleLogin}
//                         fullWidth
//                         variant="contained"
//                         sx={{
//                             mt: 3,
//                             mb: 2,
//                             backgroundColor: '#db4437', // Google's red color
//                             '&:hover': { backgroundColor: '#c23321' } // Darker red on hover
//                         }}
//                         startIcon={<GoogleIcon />
//
//                     } // Add the Google icon here
//                     >
//                         Login with Google
//                     </Button>
//                 </Box>
//             </Box>
//         </Container>
//     );
// };
//
// export default Login;