import React from "react";
import Styles from "../../Stlyes";
import { Form, Field } from "react-final-form";
import ModalForm from "../../components/form/modalForm";
import TextField from "@material-ui/core/TextField";
import AdapterDateFns from "@material-ui/lab/AdapterDateFns";
import LocalizationProvider from "@material-ui/lab/LocalizationProvider";
import DatePicker from "@material-ui/lab/DatePicker";

const onSubmit = async (values, onAdd, onUpdate, setOpen, updateValues) => {
  updateValues ? onUpdate(values) : onAdd(values);
  setOpen(false);
};

const PetForm = ({ onAdd, onUpdate, open, setOpen, updateValues }) => (
  <ModalForm open={open} setOpen={setOpen}>
    <Styles>
      <h1>{updateValues ? "Modify Pet" : "Add Pet"}</h1>
      <Form
        onSubmit={(values) =>
          onSubmit(values, onAdd, onUpdate, setOpen, updateValues)
        }
        initialValues={updateValues ? updateValues : {}}
        render={({ handleSubmit, form, submitting, pristine, values }) => (
          <form onSubmit={handleSubmit}>
            <div>
              <label>Name</label>
              <Field
                name="name"
                component="input"
                type="text"
                placeholder="Name"
              />
            </div>
            <div>
              <label>Animal</label>
              <Field name="type" component="select">
                <option />
                <option value="cat">cat</option>
                <option value="dog">dog</option>
                <option value="lizard">lizard</option>
                <option value="snake">snake</option>
                <option value="bird">bird</option>
                <option value="hamster">hamster</option>
              </Field>
            </div>
            <div>
              <Field name="birthDate">
                {(props) => (
                  <div>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                      <DatePicker
                        label="Date of Birth"
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        renderInput={(params) => <TextField {...params} />}
                      />
                    </LocalizationProvider>
                  </div>
                )}
              </Field>
            </div>
            <div className="buttons">
              <button type="submit" disabled={submitting || pristine}>
                {updateValues ? "Modify" : "Submit"}
              </button>
              <button
                type="button"
                onClick={form.reset}
                disabled={submitting || pristine}
              >
                Reset
              </button>
            </div>
          </form>
        )}
      />
    </Styles>
  </ModalForm>
);

export default PetForm;
