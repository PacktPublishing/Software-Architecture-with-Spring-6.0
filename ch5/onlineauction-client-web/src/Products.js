// src/Products.js
import React from 'react';
import { Container, Typography, Box } from '@mui/material';

const Products = () => {
    return (
        <Container component="main" maxWidth="md">
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    Products
                </Typography>
                <Box sx={{ mt: 3 }}>
                    {/* Add your product list or content here */}
                    <Typography>Product 1</Typography>
                    <Typography>Product 2</Typography>
                    <Typography>Product 3</Typography>
                </Box>
            </Box>
        </Container>
    );
};

export default Products;