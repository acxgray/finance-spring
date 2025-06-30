import { axiosInstance } from "./AxiosInstance";

export const getTransactions = async () => {
  try {
    const data = await axiosInstance.get("/api/v1/transactions");
    return data.data;
  } catch (error) {
    return error;
  }
};

export const getCategories = async () => {
  try {
    const data = await axiosInstance.get("/api/v1/categories");
    return data.data;
  } catch (error) {
    return error;
  }
};

export const getUser = async () => {
  try {
    const data = await axiosInstance.get("/api/v1/users/1");
    return data.data;
  } catch (error) {
    return error;
  }
};

export const getBills = async () => {
  try {
    const data = await axiosInstance.get("/api/v1/bills");
    return data.data;
  } catch (error) {
    return error;
  }
};

export const getTransactionById = async (id) => {
  try {
    const data = await axiosInstance.get(`/api/v1/transactions/${id}`)
    return data.data;
  } catch (error) {
    return error;
  }
};
