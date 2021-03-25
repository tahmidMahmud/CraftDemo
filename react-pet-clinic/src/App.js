import React from "react";
import "./App.css";
import { Toolbar, Tabs, Tab, AppBar, Typography } from "@material-ui/core";
import { BrowserRouter, Route, Switch, Link } from "react-router-dom";
import HomePage from "./pages/homePage";
import OwnerPage from "./pages/owners/ownerPage";
import VetPage from "./pages/vets/vetPage";
import PetPage from "./pages/pets/petPage";
import VisitPage from "./pages/visits/visitPage";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  title: {
    flexGrow: 1,
  },
}));

const App = () => {
  const classes = useStyles();
  return (
    <React.Fragment>
      <BrowserRouter>
        <Route
          path="/"
          render={(history) => (
            <AppBar color="primary" position="static">
              <Toolbar>
                <Typography variant="h6" className={classes.title}>
                  Pet Clinic
                </Typography>

                <Tabs
                  textColor="inherit"
                  indicatorColor="secondary"
                  value={
                    history.location.pathname !== "/"
                      ? history.location.pathname.includes("owners")
                        ? "/owners"
                        : history.location.pathname
                      : false
                  }
                  centered
                >
                  <Tab
                    label="Pet Owners"
                    value="/owners"
                    component={Link}
                    to="/owners"
                  />
                  <Tab label="Vets" value="/vets" component={Link} to="/vets" />
                  <Tab
                    label="Visits"
                    value={"/visits"}
                    component={Link}
                    to={"/visits"}
                  />
                </Tabs>
              </Toolbar>
            </AppBar>
          )}
        />
        <Switch>
          <Route exact path={"/"} component={HomePage} />
          <Route exact path={"/owners"} component={OwnerPage} />
          <Route path={"/vets"} component={VetPage} />
          <Route path={"/owners/:id"} component={PetPage} />
          <Route path={"/visits"} component={VisitPage} />
        </Switch>
      </BrowserRouter>
    </React.Fragment>
  );
};

export default App;
