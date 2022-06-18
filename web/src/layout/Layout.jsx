import React from "react";
import { AppNavbar } from "./components/Navbar/AppNavbar";

const Layout = ({ children }) => {
    return (
        <>
            <AppNavbar />
            { children }
        </>
    )
}

export const withLayout = ( Component ) => {
    return function withLayoutComponent(props) {
        return (
                <Layout>
                    <Component {...props} />
                </Layout>
        );
    };
};